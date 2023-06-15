package net.leomeh.tutorialmod.item;

import net.leomeh.tutorialmod.TutorialMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTab {
    public static DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TutorialMod.MOD_ID);
    public static RegistryObject<CreativeModeTab> TUTORIAL_TAB = TABS.register("mod_tab", () -> {
        return CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.LIVINGINGOT.get())).title(Component.literal("Mod Tab")).build();
    });


    public static void register(IEventBus eventBus){
        TABS.register(eventBus);
    }

}
