package com.github.uquark0.magdaq.block.entity;

import com.github.uquark0.magdaq.block.BlockManager;
import com.github.uquark0.magdaq.block.TerminalBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class BlockEntityTypeManager {
    public static BlockEntityType<TerminalBlockEntity> TERMINAL_BLOCK_ENTITY_TYPE;

    public static void registerAll() {
        TERMINAL_BLOCK_ENTITY_TYPE = Registry.register(
                Registry.BLOCK_ENTITY_TYPE,
                TerminalBlock.ID,
                BlockEntityType.Builder.create(TerminalBlockEntity::new, BlockManager.TERMINAL_BLOCK).build(null)
        );
    }
}
