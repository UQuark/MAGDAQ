package com.github.uquark0.magdaq.block.gui;

import com.github.uquark0.magdaq.html.PrintRowHTML;
import com.github.uquark0.magdaq.html.QuotationRowHTML;
import com.github.uquark0.magdaq.html.Stacker;
import com.github.uquark0.magdaq.html.TerminalScreenHTML;
import com.github.uquark0.magdaq.util.TimerCache;
import gui.ava.html.Html2Image;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class TerminalHandledScreen extends HandledScreen<TerminalScreenHandler> {
    private final TimerCache<Identifier> textureCache = new TimerCache<>(this::getTextureId, 5000);

    public TerminalHandledScreen(TerminalScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        backgroundWidth = 1025 / 4;
        backgroundHeight = 769 / 4;
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        client.getTextureManager().bindTexture(textureCache.getValue());
        drawTexture(matrices, x, y, backgroundWidth, backgroundHeight, 0, 0, backgroundWidth, backgroundHeight, backgroundWidth, backgroundHeight);
    }

    public Identifier getTextureId() {
        try {
            // Destroy old texture
            if (textureCache.getValueWithoutUpdate() != null)
                client.getTextureManager().destroyTexture(textureCache.getValueWithoutUpdate());

            // Create and register new texture
            NativeImage nativeImage = NativeImage.read(renderHTML());
            NativeImageBackedTexture nativeImageBackedTexture = new NativeImageBackedTexture(nativeImage);
            return client.getTextureManager().registerDynamicTexture("terminal_screen", nativeImageBackedTexture);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public InputStream renderHTML() throws IOException {
        Stacker prints = new Stacker(new PrintRowHTML(), 11);
        Stacker quotations = new Stacker(new QuotationRowHTML(), 36);

        TerminalScreenHTML terminalScreenHTML = new TerminalScreenHTML();
        terminalScreenHTML.linkValuesArray(quotations.getStack(), prints.getStack());

        ByteArrayOutputStream bitmapOut = new ByteArrayOutputStream();
        Html2Image.fromHtml(terminalScreenHTML.getHTML()).getImageRenderer().saveImage(bitmapOut, true);

        return new ByteArrayInputStream(bitmapOut.toByteArray());
    }
}
