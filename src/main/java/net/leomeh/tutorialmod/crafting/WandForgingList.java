package net.leomeh.tutorialmod.crafting;

import net.leomeh.tutorialmod.item.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class WandForgingList {
    public  static List<CraftingUtils> ITEMCRAFTING = List.of(
            new CraftingUtils(Items.STRING, ModItems.CATSUMMONWAND.get()),
            new CraftingUtils(Items.AMETHYST_SHARD, ModItems.ALLAYSUMMONWAND.get()),
            new CraftingUtils(ModItems.BONEBINDING.get(), ModItems.DOGSUMMONWAND.get())
    );

    public static Item getResultFromIngredients(Item ingredients){
        AtomicReference<Item> result = new AtomicReference<>();
        ITEMCRAFTING.forEach((craftingUtils -> {
            if(craftingUtils.ingredient == ingredients){
                result.set(craftingUtils.result);
            }
        }));
        return result.get();
    }
}
