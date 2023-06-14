package net.leomeh.tutorialmod.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.leomeh.tutorialmod.TutorialMod;
import net.leomeh.tutorialmod.item.ModItems;
import net.leomeh.tutorialmod.recipe.GemCuttingStationRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.leomeh.tutorialmod.block.ModBlocks;

public class WandforgerJEIRecipeType implements IRecipeCategory<GemCuttingStationRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(TutorialMod.MOD_ID, "wandforger");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(TutorialMod.MOD_ID, "textures/gui/wandforger_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public WandforgerJEIRecipeType(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.WANDFORGER_TABLE.get()));
    }
    @Override
    public RecipeType<GemCuttingStationRecipe> getRecipeType() {
        return JEITutorialModPlugin.INFUSION_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Wandforger's Table");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, GemCuttingStationRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 34, 40).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 57, 18).addItemStack(new ItemStack(ModItems.LIVINGINGOT.get(), 2));
        builder.addSlot(RecipeIngredientRole.INPUT, 103,18).addItemStack(new ItemStack(ModItems.CORE_GEM.get()));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 60).addItemStack(recipe.getResultItem());
    }
}
