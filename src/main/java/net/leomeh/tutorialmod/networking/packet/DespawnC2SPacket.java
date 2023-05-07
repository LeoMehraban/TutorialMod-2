package net.leomeh.tutorialmod.networking.packet;

import net.leomeh.tutorialmod.item.custom.SummonWands;
import net.leomeh.tutorialmod.networking.ModMessages;
import net.leomeh.tutorialmod.slots.PlayerSlotsProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class DespawnC2SPacket {
    public DespawnC2SPacket(){

    }

    public DespawnC2SPacket(FriendlyByteBuf buf){

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->{
            ServerPlayer player = context.getSender();
            doThing(player);
        });
        return true;
    }

    public static void doThing(ServerPlayer player){
        player.getCapability(PlayerSlotsProvider.PLAYER_SLOTS).ifPresent(slots -> {

            for (Entity a: SummonWands.summonedAnimals) {
                //a.die(DamageSource.GENERIC);
                if(a != null) {
                    a.setRemoved(Entity.RemovalReason.KILLED);
                    slots.addSlotsLeft(1);
                }
            }


            ModMessages.sendToPlayer(new SlotsDataSyncS2CPacket(slots.getSlotsLeft() ), player);
        });
        SummonWands.tameCounter = 0;
    }
}
