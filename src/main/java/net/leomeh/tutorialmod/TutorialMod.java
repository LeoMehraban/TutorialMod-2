package net.leomeh.tutorialmod;

import com.mojang.logging.LogUtils;
import net.leomeh.tutorialmod.block.ModBlocks;
//import net.leomeh.tutorialmod.block.entity.ModBlockEntities;
import net.leomeh.tutorialmod.block.entity.ModBlockEntities;
import net.leomeh.tutorialmod.client.SlotsHudOverlay;
import net.leomeh.tutorialmod.config.TutorialConfig;
//import net.leomeh.tutorialmod.effect.ModEffects;
//import net.leomeh.tutorialmod.entity.ModEntityTypes;
import net.leomeh.tutorialmod.entity.ModEntityTypes;
import net.leomeh.tutorialmod.item.ModItems;
import net.leomeh.tutorialmod.networking.ModMessages;
//import net.leomeh.tutorialmod.screen.slot.ModMenuTypes;
//import net.leomeh.tutorialmod.screen.slot.WandForgerMenu;
//import net.leomeh.tutorialmod.screen.slot.WandforgerMenu;
import net.leomeh.tutorialmod.screen.WandforgerScreen;
import net.leomeh.tutorialmod.screen.slot.ModMenuTypes;
import net.leomeh.tutorialmod.screen.slot.WandForgerMenu;
import net.leomeh.tutorialmod.villager.ModVillagers;
import net.leomeh.tutorialmod.world.feature.ModConfiguredFeatures;
import net.leomeh.tutorialmod.world.feature.ModPlacedFeatures;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


/*
While most of the code in this and other files were written by me
some code was written by KaupenJoe. Huge thanks to him and his tutorials
some help and ideas were provided by Emmymonchy on the PyreMarten discord server
Emmy also helped with building the Llamaman camps
Thanks to the MMD for providing java help in times of need
Thanks to Jacqueline Vile and TigerJess for the textures and animations of the Llama-Man
- Leo Mehraban, May 4, 2023
 */

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TutorialMod.MOD_ID)
public class TutorialMod {

    public static final String MOD_ID = "tutorialmod";
    private static final Logger LOGGER = LogUtils.getLogger();
    public TutorialMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModVillagers.register(modEventBus);
        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);
        ModEntityTypes.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        //modEventBus.addListener(ClientModEvents::onClientSetup);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TutorialConfig.SPEC, "tutorialmod.toml");

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModVillagers.registerPOIs();
            SpawnPlacements.register(ModEntityTypes.CHOMPER.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Mob::checkMobSpawnRules);

        });
        ModMessages.register();
        SlotsHudOverlay.SLOTS_COUNT = TutorialConfig.slots_number.get();
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(ModMenuTypes.WANDFORGER_MENU.get(), WandforgerScreen::new);
        }
    }
}
