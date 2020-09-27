package com.github.uquark0.magdaq.block;

import com.github.uquark0.magdaq.Main;
import com.github.uquark0.magdaq.block.entity.TerminalBlockEntity;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class TerminalBlock extends AbstractHorizontalFacingBlock implements BlockEntityProvider {
    public static final Identifier ID = new Identifier(Main.MODID, "terminal_block");

    protected TerminalBlock() {
        super(Settings.copy(Blocks.IRON_BLOCK), new Item.Settings().group(ItemGroup.REDSTONE), ID);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new TerminalBlockEntity();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            player.openHandledScreen((TerminalBlockEntity) world.getBlockEntity(pos));
            return ActionResult.CONSUME;
        }
    }
}
