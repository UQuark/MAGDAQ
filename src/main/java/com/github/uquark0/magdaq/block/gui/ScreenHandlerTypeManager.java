package com.github.uquark0.magdaq.block.gui;

import com.github.uquark0.magdaq.block.TerminalBlock;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;

public class ScreenHandlerTypeManager {
    public static ScreenHandlerType<TerminalScreenHandler> TERMINAL_SCREEN_HANDLER_TYPE;

    public static void registerAll() {
        TERMINAL_SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(
                TerminalBlock.ID,
                TerminalScreenHandler::new
        );
    }
}
