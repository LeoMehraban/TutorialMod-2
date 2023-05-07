package net.leomeh.tutorialmod.slots;

import net.leomeh.tutorialmod.client.SlotsHudOverlay;
import net.minecraft.nbt.CompoundTag;

public class PlayerSlots {
    public int slotsLeft;
    private int MIN_SLOTS_LEFT = 0;
    private int MAX_SLOTS_LEFT = SlotsHudOverlay.SLOTS_COUNT;
    public PlayerSlots(int i){
        slotsLeft = i;
    }
    public int getSlotsLeft(){
        return slotsLeft;
    }

    public void addSlotsLeft(int add){
        this.slotsLeft = Math.min(slotsLeft + add, MAX_SLOTS_LEFT);

    }

    public void update(boolean b){
        if(b){
            MIN_SLOTS_LEFT = -2;
        } else {
            MIN_SLOTS_LEFT = 0;
        }
        MAX_SLOTS_LEFT = SlotsHudOverlay.SLOTS_COUNT;
    }


    public void subSlotsLeft(int sub){
        this.slotsLeft = Math.max(slotsLeft - sub, MIN_SLOTS_LEFT);

    }

    public void copyFrom(PlayerSlots s){
        this.slotsLeft = s.slotsLeft;
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("slotsLeft", slotsLeft);
    }

    public void loadNBTData(CompoundTag nbt){
        slotsLeft = nbt.getInt("slotsLeft");
    }
}
