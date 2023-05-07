package net.leomeh.tutorialmod.item.custom;

import net.leomeh.tutorialmod.TutorialMod;
import net.leomeh.tutorialmod.client.SlotsHudOverlay;
import net.leomeh.tutorialmod.networking.ModMessages;
import net.leomeh.tutorialmod.networking.packet.SlotsDataSyncS2CPacket;
import net.leomeh.tutorialmod.slots.PlayerSlotsProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class DogSummonWand extends SummonWands {

   public DogSummonWand(Properties p){
       super(p);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand){
       if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND){
           player.getCapability(PlayerSlotsProvider.PLAYER_SLOTS).ifPresent(slots -> {
               if(slots.getSlotsLeft() > 0) {
                   TamableAnimal mob = (TamableAnimal) EntityType.WOLF.spawn((ServerLevel) level, new ItemStack(this), player, player.blockPosition(), MobSpawnType.MOB_SUMMONED, true, false);
                   mob.tame(player);
                   SummonWands.summonedAnimals.add(tameCounter, mob);
                   tameCounter++;
                   slots.subSlotsLeft(1);
                   player.getCooldowns().addCooldown(this,100);
                   ModMessages.sendToPlayer(new SlotsDataSyncS2CPacket(slots.getSlotsLeft()), (ServerPlayer) player);

               }
           });
       }

      return super.use(level, player, hand);
    }
    //public Player brokeCrap(Player player){
     //  return player;
    //}

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);

            if(Screen.hasShiftDown()){
                pTooltipComponents.add(Component.literal("Summons a Tamed Wolf when clicked"));
            } else {
                pTooltipComponents.add(Component.literal("Press SHIFT For Info").withStyle(ChatFormatting.YELLOW));
            }

            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);

    }

}





/*
 What we want to do
 1: Summon a dog and subtract 1 from slotsLeft
 2: if the slots are 0, don't summon a dog
 3: despawn the dogs when "k" is pressed
*/
