package com.github.uquark0.magdaq.block.entity;

import com.github.uquark0.magdaq.block.gui.TerminalScreenHandler;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class TerminalBlockEntity extends BlockEntity implements NamedScreenHandlerFactory {
    public TerminalBlockEntity() {
        super(BlockEntityTypeManager.TERMINAL_BLOCK_ENTITY_TYPE);
    }

    @Override
    public Text getDisplayName() {
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new TerminalScreenHandler(syncId, this);
    }
}
