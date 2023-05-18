package net.leomeh.tutorialmod.crafting;

import net.minecraft.world.item.Item;

public class CraftingUtils {
    public Item ingredient;
    public Item result;
    public CraftingUtils(Item ingredient, Item result){
        this.ingredient = ingredient;
        this.result = result;
    }
}
