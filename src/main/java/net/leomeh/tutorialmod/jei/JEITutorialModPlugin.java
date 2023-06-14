package net.leomeh.tutorialmod.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.leomeh.tutorialmod.TutorialMod;
import net.leomeh.tutorialmod.recipe.GemCuttingStationRecipe;
import net.leomeh.tutorialmod.recipe.ModRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEITutorialModPlugin implements IModPlugin {
    public static RecipeType<GemCuttingStationRecipe> INFUSION_TYPE =
            new RecipeType<>(WandforgerJEIRecipeType.UID, GemCuttingStationRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(TutorialMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new
                WandforgerJEIRecipeType(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<GemCuttingStationRecipe> recipesInfusing = rm.getAllRecipesFor(ModRecipes.WANDFORGER_TYPE.get());
        registration.addRecipes(INFUSION_TYPE, recipesInfusing);
    }
}
