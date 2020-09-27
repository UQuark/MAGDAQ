package com.github.uquark0.magdaq.block;

import com.github.uquark0.magdaq.util.Registrable;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AbstractHorizontalFacingBlock extends HorizontalFacingBlock implements Registrable {
    private final Identifier id;
    private final BlockItem blockItem;

    protected AbstractHorizontalFacingBlock(Settings blockSettings, Item.Settings itemSettings, Identifier id) {
        super(blockSettings);
        this.id = id;
        blockItem = new BlockItem(this, itemSettings);
    }

    @Override
    public void register() {
        Registry.register(Registry.BLOCK, id, this);
        Registry.register(Registry.ITEM, id, blockItem);
    }
}
