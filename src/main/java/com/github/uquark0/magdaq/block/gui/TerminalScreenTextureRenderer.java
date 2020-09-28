package com.github.uquark0.magdaq.block.gui;

import com.github.uquark0.magdaq.html.PrintRowHTML;
import com.github.uquark0.magdaq.html.QuotationRowHTML;
import com.github.uquark0.magdaq.html.Stacker;
import com.github.uquark0.magdaq.html.TerminalScreenHTML;
import gui.ava.html.Html2Image;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

public class TerminalScreenTextureRenderer {
    private Identifier textureId;
    private Identifier oldTextureId;
    private NativeImageBackedTexture texture;
    private final Timer timer;
    private boolean isNewTexture;

    public TerminalScreenTextureRenderer(long updatePeriod, MinecraftClient client) {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                render();
            }
        }, 0, updatePeriod);
    }

    public Identifier getTextureId() {
        return textureId;
    }

    public void registerNew(MinecraftClient client) {
        oldTextureId = textureId;
        textureId = client.getTextureManager().registerDynamicTexture("terminal_screen", texture);
    }

    public void destroyOld(MinecraftClient client) {
        if (oldTextureId != null)
            client.getTextureManager().destroyTexture(oldTextureId);
    }

    public void kill(MinecraftClient client) {
        timer.cancel();
        if (oldTextureId != null)
            client.getTextureManager().destroyTexture(oldTextureId);
        if (textureId != null)
            client.getTextureManager().destroyTexture(textureId);
    }

    public boolean popNewTexture() {
        if (isNewTexture) {
            isNewTexture = false;
            return true;
        }
        return false;
    }

    private void render() {
        try {
            NativeImage nativeImage = NativeImage.read(renderHTML());
            texture = new NativeImageBackedTexture(nativeImage);
            isNewTexture = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private InputStream renderHTML() throws IOException {
        Stacker prints = new Stacker(new PrintRowHTML(), 11);
        Stacker quotations = new Stacker(new QuotationRowHTML(), 36);

        TerminalScreenHTML terminalScreenHTML = new TerminalScreenHTML();
        terminalScreenHTML.linkValuesArray(quotations.getStack(), prints.getStack());

        ByteArrayOutputStream bitmapOut = new ByteArrayOutputStream();
        Html2Image.fromHtml(terminalScreenHTML.getHTML()).getImageRenderer().saveImage(bitmapOut, true);

        return new ByteArrayInputStream(bitmapOut.toByteArray());
    }
}
