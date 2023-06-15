package net.leomeh.tutorialmod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.leomeh.tutorialmod.TutorialMod;
import net.leomeh.tutorialmod.config.TutorialConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.IForgeGuiGraphics;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class SlotsHudOverlay {
    public static int SLOTS_COUNT = 5;
    public static final ResourceLocation EMPTY_SLOT = new ResourceLocation(TutorialMod.MOD_ID, "textures/soul.png");
    public static final ResourceLocation FILLED_SLOT = new ResourceLocation(TutorialMod.MOD_ID, "textures/soul2.png");


    public static final IGuiOverlay HUD_THIRST = ((gui, guiGraphics ,poseStack, partialTick, width) -> {
        int x = width / 2;
        int y = width;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, FILLED_SLOT);
        if((ClientSlotsData.getPlayerSlots() != SLOTS_COUNT)/* == !TutorialConfig.show_full_slots.get() || ClientSlotsData.getPlayerSlots() == SLOTS_COUNT */) {
            for (int i = 0; i < SLOTS_COUNT; i++) {
                guiGraphics.blit(FILLED_SLOT, x - 94 + (i * 9), y - 54, 0, 0, 12, 12,
                        12, 12);
            }

            RenderSystem.setShaderTexture(0, EMPTY_SLOT);
            for (int i = 0; i < SLOTS_COUNT; i++) {
                if (ClientSlotsData.getPlayerSlots() > i) {
                    guiGraphics.blit(EMPTY_SLOT, x - 94 + (i * 9), y - 54, 0, 0, 12, 12,
                            12, 12);
                } else {
                    break;
                }
            }
        }
    });
}
