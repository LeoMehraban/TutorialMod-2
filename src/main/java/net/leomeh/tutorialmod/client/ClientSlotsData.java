package net.leomeh.tutorialmod.client;

public class ClientSlotsData {
    public static int playerSlots;
    public static void set(int slots){
        ClientSlotsData.playerSlots = slots;
    }

    public static int getPlayerSlots(){
        return playerSlots;
    }
}
