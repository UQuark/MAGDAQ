package com.github.uquark0.magdaq.block.gui;

import com.github.uquark0.magdaq.economy.*;
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
    public static final int PRINTS_LIMIT = 8;
    public static final int QUOTATION_LIMIT = 26;

    private Identifier textureId;
    private Identifier oldTextureId;
    private NativeImageBackedTexture texture;
    private final Timer timer;
    private boolean isNewTexture;
    private TerminalScreenHandler handler;

    public TerminalScreenTextureRenderer(long updatePeriod, TerminalScreenHandler handler) {
        this.handler = handler;
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
        Stacker printsStacker = new Stacker(new PrintRowHTML(), PRINTS_LIMIT);
        Stacker quotationStacker = new Stacker(new QuotationRowHTML(), QUOTATION_LIMIT);

        Prints prints = handler.getPrints(PRINTS_LIMIT);
        Quotation quotation = handler.getQuotation(QUOTATION_LIMIT);

        for (PrintRow p : prints.prints)
            printsStacker.addValues(p.price.toString(), String.valueOf(p.volume));

        for (int i = 0; i < Math.max(quotation.bid.size(), quotation.ask.size()); i++)
            if (i >= quotation.bid.size()) {
                quotationStacker.addValues("", "", quotation.ask.get(i).price.toString(), String.valueOf(quotation.ask.get(i).volume));
            } else if (i >= quotation.ask.size()) {
                quotationStacker.addValues(quotation.bid.get(i).price.toString(), String.valueOf(quotation.bid.get(i).volume), "", "");
            } else {
                quotationStacker.addValues(
                        quotation.bid.get(i).price.toString(),
                        String.valueOf(quotation.bid.get(i).volume),
                        quotation.ask.get(i).price.toString(),
                        String.valueOf(quotation.ask.get(i).volume)
                );
            }

        TerminalScreenHTML terminalScreenHTML = new TerminalScreenHTML();
        terminalScreenHTML.linkValuesArray(quotationStacker.getStack(), printsStacker.getStack());

        ByteArrayOutputStream bitmapOut = new ByteArrayOutputStream();
        Html2Image.fromHtml(terminalScreenHTML.getHTML()).getImageRenderer().saveImage(bitmapOut, true);

        return new ByteArrayInputStream(bitmapOut.toByteArray());
    }
}
