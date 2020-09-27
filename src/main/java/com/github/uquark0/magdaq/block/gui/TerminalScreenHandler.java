package com.github.uquark0.magdaq.block.gui;

import com.github.uquark0.magdaq.block.entity.TerminalBlockEntity;
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
}
