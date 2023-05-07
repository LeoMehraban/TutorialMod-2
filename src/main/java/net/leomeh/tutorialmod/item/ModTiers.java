package net.leomeh.tutorialmod.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {
    public static final ForgeTier LIVING = new ForgeTier(2, 1400, 2f, 2, 10, BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ModItems.LIVINGINGOT.get()));

    public static final ForgeTier DEATHSTEEL = new ForgeTier(2, 1400, 2f, 4, 1, BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ModItems.DEATHSTEEL_INGOT.get()));

}
