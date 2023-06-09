package net.leomeh.tutorialmod.screen;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.leomeh.tutorialmod.TutorialMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class WandforgerScreen extends AbstractContainerScreen<WandForgerMenu>{
        private static final ResourceLocation TEXTURE =
                new ResourceLocation(TutorialMod.MOD_ID, "textures/gui/wandforger_gui.png");

        public WandforgerScreen(WandForgerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
            super(pMenu, pPlayerInventory, pTitle);
        }

        @Override
        protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, TEXTURE);
            int x = (width - imageWidth) / 2;
            int y = (height - imageHeight) / 2;

            guiGraphics.blit(TEXTURE,x, y, 0, 0, imageWidth, imageHeight);

            if(menu.isCrafting()) {
                guiGraphics.blit(TEXTURE,x + 102, y + 41, 176, 0, 8, menu.getScaledProgress());
            }
        }

        @Override
        public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
            renderBackground(guiGraphics);
            super.render(guiGraphics, mouseX, mouseY, delta);
            renderTooltip(guiGraphics, mouseX, mouseY);
        }

}
