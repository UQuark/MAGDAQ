package com.github.uquark0.magdaq.block;

public class BlockManager {
    public static final TerminalBlock TERMINAL_BLOCK = new TerminalBlock();

    public static void registerAll() {
        TERMINAL_BLOCK.register();
    }
}
