package com.github.uquark0.magdaq.block.gui;

import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class HandledScreenManager {
    public static void registerAll() {
        ScreenRegistry.register(
                ScreenHandlerTypeManager.TERMINAL_SCREEN_HANDLER_TYPE,
                TerminalHandledScreen::new
        );
    }
}
