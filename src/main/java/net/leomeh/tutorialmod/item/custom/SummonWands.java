package net.leomeh.tutorialmod.item.custom;

import net.leomeh.tutorialmod.client.SlotsHudOverlay;
import net.leomeh.tutorialmod.networking.ModMessages;
import net.leomeh.tutorialmod.networking.packet.DespawnC2SPacket;
import net.leomeh.tutorialmod.networking.packet.SlotsDataSyncS2CPacket;
import net.leomeh.tutorialmod.slots.PlayerSlotsProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;

public class SummonWands extends Item {
    public static ArrayList<Entity> summonedAnimals = new ArrayList<>();
    public static int tameCounter = 0;
    //public static boolean hasArmorOn = false;

    public SummonWands(Properties p){
        super(p);
    }

    public static void onPutOnArmor(Player player) {
        if(!player.getLevel().isClientSide()) {
            player.getCapability(PlayerSlotsProvider.PLAYER_SLOTS).ifPresent(slots -> {
                SlotsHudOverlay.SLOTS_COUNT += 2;
                slots.update(true);
                System.out.print("[tutorialmod] Coffeecake 3");
                System.out.print(SlotsHudOverlay.SLOTS_COUNT);
                slots.addSlotsLeft(2);
                System.out.print("[tutorialmod] Coffeecake 4");
                System.out.print(slots.getSlotsLeft());
                ModMessages.sendToPlayer(new SlotsDataSyncS2CPacket(slots.getSlotsLeft() ), (ServerPlayer) player);
            });
            DespawnC2SPacket.doThing((ServerPlayer) player);
            System.out.print("[tutorialmod] Coffeecake 1");
        }
    }

    public static void onTakeOffArmor(Player player) {
        if(!player.getLevel().isClientSide()) {
            DespawnC2SPacket.doThing((ServerPlayer) player);
            SlotsHudOverlay.SLOTS_COUNT -= 2;
            player.getCapability(PlayerSlotsProvider.PLAYER_SLOTS).ifPresent(slots -> {
                slots.update(false);
            });
            System.out.print("[tutorialmod] Coffeecake 2");
            System.out.print(SlotsHudOverlay.SLOTS_COUNT);
        }
    }

    public Entity summonAnimal(ServerLevel level, ItemStack item, Player player, EntityType entity){
        Entity animal =  entity.spawn(level, item, player,new BlockPos(player.getPosition(1).x, player.getPosition(1).y, player.getPosition(1).z), MobSpawnType.MOB_SUMMONED, false, false);
        return animal;
    }

}
