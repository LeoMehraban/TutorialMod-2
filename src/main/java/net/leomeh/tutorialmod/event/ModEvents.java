package net.leomeh.tutorialmod.event;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.leomeh.tutorialmod.TutorialMod;
import net.leomeh.tutorialmod.entity.LlamamanEntity;
import net.leomeh.tutorialmod.entity.ModEntityTypes;
import net.leomeh.tutorialmod.item.ModArmorMaterials;
import net.leomeh.tutorialmod.item.ModItems;
import net.leomeh.tutorialmod.networking.ModMessages;
import net.leomeh.tutorialmod.networking.packet.SlotsDataSyncS2CPacket;
import net.leomeh.tutorialmod.slots.PlayerSlots;
import net.leomeh.tutorialmod.slots.PlayerSlotsProvider;
import net.leomeh.tutorialmod.villager.ModVillagers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public class ModEvents {
    @Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID)
    public static class ForgeEvents {

        @SubscribeEvent
        public static void onLivingChangeTargetEvent(LivingChangeTargetEvent event){
            if(event.getEntity().getType() == ModEntityTypes.CHOMPER.get()) {

                if (event.getNewTarget() != null) {
                    event.getNewTarget().getArmorSlots().forEach((ItemStack itemStack) -> {
                        if (itemStack.getItem() instanceof ArmorItem armorItem) {
                            if (armorItem.getMaterial() == ModArmorMaterials.LLAMA_LEATHER) {
                                event.setCanceled(true);
                            }
                        }
                    });

                }
            }

        }
        @SubscribeEvent
        public static void addCustomTrades(VillagerTradesEvent event) {
            if (event.getType() == ModVillagers.WANDFORGER.get()) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                ItemStack stack = new ItemStack(ModItems.BONEBINDING.get(), 1);
                int villagerLevel = 1;

                trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(Items.EMERALD, 2),
                        stack, 10, 8, 0.02F));
            }

            if (event.getType() == ModVillagers.WANDFORGER.get()) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                ItemStack stack = new ItemStack(ModItems.LIVINGSTONE_PEBBLE.get(), 15);
                int villagerLevel = 1;

                trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(Items.EMERALD, 5),
                        stack, 10, 8, 0.02F));
            }
            if (event.getType() == ModVillagers.WANDFORGER.get()) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                ItemStack stack = new ItemStack(Items.EMERALD, 10);
                int villagerLevel = 2;

                trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(ModItems.LIVINGINGOT.get(), 2),
                        stack, 10, 8, 0.02F));
            }

            if (event.getType() == ModVillagers.WANDFORGER.get()) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                ItemStack stack = new ItemStack(ModItems.DOGSUMMONWAND.get(), 1);
                int villagerLevel = 2;

                trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(Items.EMERALD, 5),
                        stack, 10, 8, 0.02F));
            }
        }

        @SubscribeEvent
        public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof Player) {
                if (!event.getObject().getCapability(PlayerSlotsProvider.PLAYER_SLOTS).isPresent()) {
                    event.addCapability(new ResourceLocation(TutorialMod.MOD_ID, "properties"), new PlayerSlotsProvider());
                }
            }
        }

        @SubscribeEvent
        public static void onLivingDamage(LivingDamageEvent e) {
            Entity entity = e.getEntity();
            Entity entity1 = e.getSource().getEntity();
            if (entity instanceof LivingEntity livingEntity) {
                if (isWearingDeathSteel(livingEntity)) {
                    if (entity1 instanceof LivingEntity livingEntity1) {
                        livingEntity1.addEffect(new MobEffectInstance(MobEffects.WITHER, 40, 2));
                        System.out.println("[tutorialmod] Coffeecake 7");
                    }
                }
            }

        }

        public static boolean isWearingDeathSteel(LivingEntity pLivingEntity) {
            for (ItemStack itemstack : pLivingEntity.getArmorSlots()) {
                if (deathSteel(itemstack)) {
                    System.out.println("[tutorialmod] Coffeecake 8 [true]");
                    return true;
                }
            }
            System.out.println("[tutorialmod] Coffeecake 8 [false]");
            return false;
        }

        public static boolean deathSteel(ItemStack itemStack) {
            return itemStack.getItem() instanceof ArmorItem && ((ArmorItem) itemStack.getItem()).getMaterial() == ModArmorMaterials.DEATHSTEEL;
        }

        @SubscribeEvent
        public static void onPlayerCloned(PlayerEvent.Clone event) {
            if (event.isWasDeath()) {
                event.getOriginal().getCapability(PlayerSlotsProvider.PLAYER_SLOTS).ifPresent(oldStore -> {
                    event.getOriginal().getCapability(PlayerSlotsProvider.PLAYER_SLOTS).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
            }
        }

        @SubscribeEvent
        public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
            if (!event.getLevel().isClientSide()) {
                if (event.getEntity() instanceof ServerPlayer player) {
                    player.getCapability(PlayerSlotsProvider.PLAYER_SLOTS).ifPresent(slots -> {
                        ModMessages.sendToPlayer(new SlotsDataSyncS2CPacket(slots.getSlotsLeft()), player);
                    });
                }
            }
        }

        @SubscribeEvent
        public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
            event.register(PlayerSlots.class);
        }
    }

    @Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(ModEntityTypes.CHOMPER.get(), LlamamanEntity.setAttributes());
        }

    }
}
