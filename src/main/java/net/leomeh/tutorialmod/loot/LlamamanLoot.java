package net.leomeh.tutorialmod.loot;

import net.leomeh.tutorialmod.item.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;

public class LlamamanLoot {
    public static ModLootUtils LOOT = new ModLootUtils(List.of(
            new ModLootUtils.ModLoot(Items.ACACIA_PLANKS, 2, 4).get(),
            new ModLootUtils.ModLoot(ModItems.LLAMA_LEATHER.get(), 1, 3).get(),
            new ModLootUtils.ModLoot(Items.IRON_INGOT, 1, 2).get(),
            new ModLootUtils.ModLoot(ModItems.LLAMA_LEATHER_HELMET.get(), 1, 1).get(),
            new ModLootUtils.ModLoot(Items.ACACIA_LOG, 1, 2).get(),
            new ModLootUtils.ModLoot(Items.STRIPPED_ACACIA_LOG, 1,2).get(),
            new ModLootUtils.ModLoot(ModItems.CATSUMMONWAND.get(), 1,1).get(),
            new ModLootUtils.ModLoot(ModItems.CORE_GEM.get(), 1,2).get(),
            new ModLootUtils.ModLoot(Items.LEAD, 1,3).get(),
            new ModLootUtils.ModLoot(Items.STRING, 4, 16).get()
    ));
}
