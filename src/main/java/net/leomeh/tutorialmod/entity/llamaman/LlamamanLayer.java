package net.leomeh.tutorialmod.entity.llamaman;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.leomeh.tutorialmod.TutorialMod;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class LlamamanLayer extends GeoEntityRenderer<LlamamanEntity> {
    public LlamamanLayer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new Llamaman());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(LlamamanEntity instance) {
        return new ResourceLocation(TutorialMod.MOD_ID, "textures/entity/llamaman2.png");
    }

    @Override
    public RenderType getRenderType(LlamamanEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }
}