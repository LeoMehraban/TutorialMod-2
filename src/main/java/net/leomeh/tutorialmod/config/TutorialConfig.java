package net.leomeh.tutorialmod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class TutorialConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final  ForgeConfigSpec.ConfigValue<Integer> slots_number;

    //public static final ForgeConfigSpec.ConfigValue<Boolean> show_full_slots;

    static {
        BUILDER.push("CONFIG FOR SLOTS");
        slots_number = BUILDER.comment("The Amount Of Slots a Player has. Default value is 5.").define("Slots", 5);
        //show_full_slots = BUILDER.comment("Weather slots are shown when full. Default is false. Does nothing ATM").define("Show Slots when full", false);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
