package net.leomeh.tutorialmod.event;

import net.leomeh.tutorialmod.TutorialMod;
import net.leomeh.tutorialmod.client.SlotsHudOverlay;
import net.leomeh.tutorialmod.entity.Llamaman;
import net.leomeh.tutorialmod.entity.LlamamanEntity;
import net.leomeh.tutorialmod.entity.LlamamanLayer;
import net.leomeh.tutorialmod.entity.ModEntityTypes;
import net.leomeh.tutorialmod.networking.ModMessages;
import net.leomeh.tutorialmod.networking.packet.DespawnC2SPacket;
import net.leomeh.tutorialmod.util.KeyBinding;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {
    public static ModelLayerLocation LLAMAMAN_LAYER = new ModelLayerLocation(new ResourceLocation(TutorialMod.MOD_ID, "llamaman"), "llamaman");
    @Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents{

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event){
            if(KeyBinding.DESPWAN_KEY.consumeClick()) {
                ModMessages.sendToServer(new DespawnC2SPacket());

            }
        }


    }
    @Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public  static class ClientBusModEvents{
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event){
            event.register(KeyBinding.DESPWAN_KEY);
        }

        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event){
            event.registerAboveAll("slots", SlotsHudOverlay.HUD_THIRST);
        }

//        @SubscribeEvent
//        public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
//            event.registerLayerDefinition(Llamaman.LAYER_LOCATION, Llamaman::createBodyLayer);
//        }



        @SubscribeEvent
        public static void registerRenderer(EntityRenderersEvent.RegisterRenderers event){

            event.registerEntityRenderer(ModEntityTypes.CHOMPER.get(), LlamamanLayer::new);
        }


    }


}
