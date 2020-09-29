package com.github.uquark0.magdaq;

import com.github.uquark0.magdaq.block.BlockManager;
import com.github.uquark0.magdaq.block.entity.BlockEntityTypeManager;
import com.github.uquark0.magdaq.block.gui.HandledScreenManager;
import com.github.uquark0.magdaq.block.gui.ScreenHandlerTypeManager;
import net.fabricmc.api.ModInitializer;

import java.util.Random;

public class Main implements ModInitializer {
    public static final String MODID = "magdaq";
    public static final Random RANDOM = new Random();

    static {
        BlockManager.registerAll();
        BlockEntityTypeManager.registerAll();
        ScreenHandlerTypeManager.registerAll();
        HandledScreenManager.registerAll();
    }

    @Override
    public void onInitialize() {
    }
}
