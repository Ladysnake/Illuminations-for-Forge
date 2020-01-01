package ladysnake.illuminations.block;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;

public class FireFlyTallGrassBlock extends DoublePlantBlock {

    public FireFlyTallGrassBlock(Properties properties) {

        super(properties);
    }

    @Override
    public boolean ticksRandomly(BlockState state)
    {

        return true;
    }

    @Override
    public int tickRate(IWorldReader worldIn)
    {

        return 1;
    }

    // tick
    @Override
    public void func_225534_a_(BlockState state, ServerWorld world, BlockPos pos, Random random)
    {

        FireFlyBlockTick.tick(state, world, pos, random);
    }
}
