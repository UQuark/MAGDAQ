package com.github.uquark0.magdaq.block.gui;

import com.github.uquark0.magdaq.Main;
import com.github.uquark0.magdaq.block.entity.TerminalBlockEntity;
import com.github.uquark0.magdaq.economy.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;

public class TerminalScreenHandler extends ScreenHandler {
    private final TerminalBlockEntity owner;
    private final boolean isClient;

    public TerminalScreenHandler(int syncId) {
        super(ScreenHandlerTypeManager.TERMINAL_SCREEN_HANDLER_TYPE, syncId);
        owner = null;
        isClient = true;
    }

    public TerminalScreenHandler(int syncId, TerminalBlockEntity owner) {
        super(ScreenHandlerTypeManager.TERMINAL_SCREEN_HANDLER_TYPE, syncId);
        isClient = (owner == null);
        this.owner = owner;
    }

    public TerminalScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    public Prints getPrints(int limit) {
        int count = limit;
        Prints result = new Prints();
        for (int i = 0; i < count; i++) {
            int price = Math.abs(Main.RANDOM.nextInt()) % 60000;
            int volume = Math.abs(Main.RANDOM.nextInt()) % 9 + 1;
            result.prints.add(new PrintRow(new MoneyAmount(price), volume));
        }
        return result;
    }

    public Quotation getQuotation(int limit) {
        int bidCount = limit;
        int askCount = limit;
        Quotation result = new Quotation();
        for (int i = 0; i < bidCount; i++) {
            int price = Math.abs(Main.RANDOM.nextInt()) % 60000;
            int volume = Math.abs(Main.RANDOM.nextInt()) % 9 + 1;
            result.bid.add(new QuotationRow(new MoneyAmount(price), volume));
        }
        for (int i = 0; i < askCount; i++) {
            int price = Math.abs(Main.RANDOM.nextInt()) % 60000;
            int volume = Math.abs(Main.RANDOM.nextInt()) % 9 + 1;
            result.ask.add(new QuotationRow(new MoneyAmount(price), volume));
        }
        return result;
    }
}
