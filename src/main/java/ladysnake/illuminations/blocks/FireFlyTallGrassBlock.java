package ladysnake.illuminations.blocks;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class FireFlyTallGrassBlock extends DoublePlantBlock {

    public FireFlyTallGrassBlock(Properties properties) {

        super(properties);
    }

    @Override
    public boolean ticksRandomly(BlockState state) {

        return true;
    }

    @Override
    public int tickRate(IWorldReader worldIn) {

        return 1;
    }

    @Override
    public void tick(BlockState state, World world, BlockPos pos, Random random) {

        FireFlyBlockTick.tick(state, world, pos, random);
        
        super.tick(state, world, pos, random);
    }
}
