package net.leomeh.tutorialmod.networking;

import net.leomeh.tutorialmod.TutorialMod;
import net.leomeh.tutorialmod.networking.packet.DespawnC2SPacket;
import net.leomeh.tutorialmod.networking.packet.SlotsDataSyncS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private  static SimpleChannel INSTANCE;
    private static int packetId = 0;

    private static  int id(){
       return packetId++;
    }

    public static void register(){
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(TutorialMod.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();
        INSTANCE = net;

        net.messageBuilder(DespawnC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(DespawnC2SPacket::new)
                .encoder(DespawnC2SPacket::toBytes)
                .consumerMainThread(DespawnC2SPacket::handle)
                .add();
        net.messageBuilder(SlotsDataSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SlotsDataSyncS2CPacket::new)
                .encoder(SlotsDataSyncS2CPacket::toBytes)
                .consumerMainThread(SlotsDataSyncS2CPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message){
        INSTANCE.sendToServer(message);
    }
    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player){
        INSTANCE.send(PacketDistributor.PLAYER.with(()-> player),message);
    }
}
