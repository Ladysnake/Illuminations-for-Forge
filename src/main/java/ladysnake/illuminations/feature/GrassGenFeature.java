package ladysnake.illuminations.feature;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import ladysnake.illuminations.mod.Illuminations;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class GrassGenFeature extends Feature<ProbabilityConfig> {

    public GrassGenFeature(Function<Dynamic<?>, ? extends ProbabilityConfig> configFactoryIn) {

        super(configFactoryIn);
    }

    @Override
    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random random, BlockPos pos, ProbabilityConfig config) {

        int i = 0;

        for (int j = 0; j < 64; ++j) {
            BlockPos blockpos = pos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
            if (world.isAirBlock(blockpos) && blockpos.getY() < 255
                    && Illuminations.ObjectHolders.GRASS_BLOCK.getDefaultState().isValidPosition(world, blockpos)) {

                if (random.nextBoolean())
                    ((DoublePlantBlock) Illuminations.ObjectHolders.TALL_GRASS_BLOCK).placeAt(world, blockpos, 2);
                else
                    world.setBlockState(blockpos, Illuminations.ObjectHolders.TALL_GRASS_BLOCK.getDefaultState(), 2);
                ++i;
            }
        }
        return i > 0;
    }
}