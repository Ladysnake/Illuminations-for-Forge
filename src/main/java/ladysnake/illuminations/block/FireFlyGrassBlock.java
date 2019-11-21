package ladysnake.illuminations.block;

import java.util.Random;

import ladysnake.illuminations.mod.Illuminations;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.block.TallGrassBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class FireFlyGrassBlock extends TallGrassBlock {

    public FireFlyGrassBlock(Properties properties) {

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

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, BlockState state) {

        DoublePlantBlock doubleplantblock = (DoublePlantBlock) Illuminations.ObjectHolders.FIREFLY_GRASS_BLOCK;

        if (doubleplantblock.getDefaultState().isValidPosition(worldIn, pos) && worldIn.isAirBlock(pos.up())) {
            doubleplantblock.placeAt(worldIn, pos, 2);
        }
    }
}
