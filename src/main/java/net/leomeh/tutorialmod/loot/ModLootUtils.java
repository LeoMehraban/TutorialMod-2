package net.leomeh.tutorialmod.loot;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModLootUtils {
    List<ItemStack> mainMap;
    ModLootUtils(List<ItemStack> lootlist){
        this.mainMap = lootlist;
    }

    public ItemStack getRandomValue(){
        ItemStack value1 = mainMap.get((int) Math.floor(Math.random() * mainMap.size()));
        return value1;
    }
    public static class ModLoot{
        int minCount;
        int maxCount;
        Item item;

        ModLoot(Item item, int minCount, int maxCount){
            this.minCount = minCount;
            this.maxCount = maxCount;
            this.item = item;
        }
        public ItemStack get(){
            ItemStack itemStack = new ItemStack(item);
            itemStack.setCount((int) Math.floor(Math.random() * maxCount + (minCount)));
            return itemStack;
        }
    }

}
