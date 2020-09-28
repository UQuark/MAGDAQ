package com.github.uquark0.magdaq.block.gui;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class TerminalHandledScreen extends HandledScreen<TerminalScreenHandler> {
    private final TerminalScreenTextureRenderer textureRenderer = new TerminalScreenTextureRenderer(5000, client);

    public TerminalHandledScreen(TerminalScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        backgroundWidth = (int) (1025 / 3.5);
        backgroundHeight = (int) (769 / 3.5);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        if (textureRenderer.popNewTexture()) {
            textureRenderer.registerNew(client);
            textureRenderer.destroyOld(client);
            drawCenteredString(matrices, client.textRenderer, "UPDATING...", width / 2, height / 2, 0xFFFFFF);
            return;
        }
        final Identifier textureId = textureRenderer.getTextureId();
        if (textureId == null) {
            drawCenteredString(matrices, client.textRenderer, "LOADING...", width / 2, height / 2, 0xFFFFFF);
            return;
        }
        client.getTextureManager().bindTexture(textureId);
        drawTexture(matrices, x, y, backgroundWidth, backgroundHeight, 0, 0, backgroundWidth, backgroundHeight, backgroundWidth, backgroundHeight);
    }

    @Override
    public void removed() {
        textureRenderer.kill(client);
    }
}
