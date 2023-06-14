package net.leomeh.tutorialmod.entity.util;


import net.leomeh.tutorialmod.entity.llamaman.LlamamanEntity;
import net.leomeh.tutorialmod.loot.ModLootUtils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

//Code written by SkyJay

public interface TradingMob {

    default void tradeWithDroppedItem(PathfinderMob self, ItemEntity item1, ModLootUtils lootUtils){
        getTradeResult((PathfinderMob) this, item1.getItem(), lootUtils).forEach(item -> BehaviorUtils.throwItem(self, item, getTradeTargetPosition(self, null)));
    }

    /**
     * @return the player who is trading, or null if the mob is not trading
     */
    @Nullable
    Player getTradingPlayer();

    /**
     * @param player the player to begin trading
     */
    void setTradingPlayer(@Nullable Player player);

    /**
     * @param self   the trading mob
     * @param player the player
     * @return true if the given player is allowed to trade with this entity
     */
    default boolean canPlayerTrade(final PathfinderMob self, final Player player) {
        return player != null && player != self.getTarget();
    }

    /**
     * @return true if the trading player exists
     */
    default boolean isTrading() {
        return this.getTradingPlayer() != null;
    }

    public Item getTradingItem();

    /**
     * @return an Item Tag of items to accept from the player while trading
     **/
    //TagKey<Item> getTradeTag();

    /**
     * @return the ID of a loot table for trade results
     */
    ResourceLocation getTradeLootTable();

    /**
     * @return true if angry particles should appear when a trade fails
     */
    default boolean sendAngryParticlesOnFail() {
        return true;
    }

    /**
     * Creates a list of trade results by querying the loot table
     *
     * @param tradeItem the trade offering
     * @return a list of result items
     */
    default List<ItemStack> getTradeResult(final PathfinderMob self, final ItemStack tradeItem, ModLootUtils lootTable) {
        if(tradeItem.getItem() == getTradingItem()) {
            return List.of(lootTable.getRandomValue());
        } else {
            return List.of(ItemStack.EMPTY);
        }
    }

    /**
     * Performs a trade by depleting the tradeItem and creating a resultItem
     *
     * @param self      the trading mob
     * @param player    the player, if any
     * @param tradeItem the item offered by the player
     */
    default boolean trade(final PathfinderMob self, @Nullable final Player player, final ItemStack tradeItem, ModLootUtils lootUtils, Predicate<Player> canTradeWith) {
        if(canTradeWith.test(player)) {
            final Vec3 tradeTargetPos = getTradeTargetPosition(self, player);
            // determine list of trade results
            // drop trade results as item entities
            getTradeResult(self, tradeItem, lootUtils).forEach(item -> BehaviorUtils.throwItem(self, item, tradeTargetPos));
            // shrink/remove held item
            tradeItem.shrink(1);
            self.setItemInHand(InteractionHand.MAIN_HAND, tradeItem);
            if (tradeItem.getCount() <= 0) {
                this.setTradingPlayer(null);
            }
            // spawn xp orb
            if (player != null && self.getRandom().nextInt(3) == 0) {
                self.level.addFreshEntity(new ExperienceOrb(self.level, self.getX(), self.getY(), self.getZ(), 1 + self.getRandom().nextInt(2)));
            }
            return true;
        } else {
            return false;
        }
    }

    default InteractionResult startTrading(final PathfinderMob self, final Player player, final InteractionHand hand, Item tradingItem) {
        ItemStack stack = player.getItemInHand(hand);
        // check if the tradingPlayer is holding a trade item and the entity is not already trading
        if (!isTrading() && LlamamanEntity.targetHasLlamaLeather(player) && self.getOffhandItem().isEmpty()
                && !stack.isEmpty() && stack.getItem() == tradingItem) {
            // determine if player is eligible
            if (canPlayerTrade(self, player)) {
                // initiate trading
                this.setTradingPlayer(player);
                // take the item from the tradingPlayer
                self.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(stack.getItem()));
                if (!player.isCreative()) {
                    stack.shrink(1);
                }
                player.setItemInHand(hand, stack);
                return InteractionResult.SUCCESS;
            } else if (sendAngryParticlesOnFail() && self.level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.ANGRY_VILLAGER, self.getX(), self.getEyeY(), self.getZ(), 4, 0, 0, 0, 0);
            }
        }

        return InteractionResult.PASS;
    }

    /**
     * Determines the landing zone for the trade results
     *
     * @param self   the trading mob
     * @param player the trading player, if any
     * @return the target position
     */
    default Vec3 getTradeTargetPosition(final PathfinderMob self, @Nullable final Player player) {
        Vec3 tradeTarget;
        if (player != null) {
            tradeTarget = player.getEyePosition();
        } else {
            tradeTarget = LandRandomPos.getPos(self, 4, 2);
            if (null == tradeTarget) {
                tradeTarget = self.getEyePosition();
            }
        }
        return tradeTarget;
    }
}
