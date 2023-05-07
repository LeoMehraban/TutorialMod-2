package net.leomeh.tutorialmod.networking.packet;

import net.leomeh.tutorialmod.client.ClientSlotsData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SlotsDataSyncS2CPacket {
    private final int slots;
    public SlotsDataSyncS2CPacket(int slots){
        this.slots = slots;
    }

    public SlotsDataSyncS2CPacket(FriendlyByteBuf buf){
        this.slots = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(slots);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->{
            ClientSlotsData.set(slots);
        });
        return true;
    }
}
