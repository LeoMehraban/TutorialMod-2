package net.leomeh.tutorialmod.entity;// Made with Blockbench 4.7.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.leomeh.tutorialmod.TutorialMod;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import software.bernie.geckolib3.model.AnimatedGeoModel;


	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor



	public class Llamaman extends AnimatedGeoModel<LlamamanEntity> {
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
