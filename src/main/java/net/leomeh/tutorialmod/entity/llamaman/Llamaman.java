package net.leomeh.tutorialmod.entity.llamaman;// Made with Blockbench 4.7.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.leomeh.tutorialmod.TutorialMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;


	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor



	public class Llamaman extends GeoModel<LlamamanEntity> {
		@Override
		public ResourceLocation getModelResource(LlamamanEntity object) {
			return new ResourceLocation(TutorialMod.MOD_ID, "geo/llamaman.geo.json");
		}

		@Override
		public ResourceLocation getTextureResource(LlamamanEntity object) {
			return new ResourceLocation(TutorialMod.MOD_ID, "textures/entity/llamaman2.png");
		}

		@Override
		public ResourceLocation getAnimationResource(LlamamanEntity animatable) {
			return new ResourceLocation(TutorialMod.MOD_ID, "animations/model.animation.json");
		}
	}
