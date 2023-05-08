package net.leomeh.tutorialmod.entity;

//package net.kaupenjoe.tutorialmod.entity;

import net.leomeh.tutorialmod.TutorialMod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TutorialMod.MOD_ID);

    public static final RegistryObject<EntityType<LlamamanEntity>> CHOMPER =
            ENTITY_TYPES.register("llamaman",
                    () -> EntityType.Builder.of(LlamamanEntity::new, MobCategory.CREATURE)
                            .sized(1.4f, 3.0f)
                            .build(new ResourceLocation(TutorialMod.MOD_ID, "llamaman").toString()));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
