package ladysnake.illuminations.feature;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import ladysnake.illuminations.mod.ConfigData;
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

        // default value is 1 ...
        for (int j = 0; j < ConfigData.rarity; ++j) {

            // so this only runs once.
            // with higher config options, one value in 4 is skipped,
            // so statistically a value of 16 means 4 guaranteed grass generations per chunk
            if (random.nextInt(4) > 0)
                continue;

            BlockPos blockpos = pos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));

            int pack_size = random.nextInt(8) + 3;

            for (int pack = 0; pack < pack_size * 2; pack++) {
                BlockPos pos_around = blockpos.add(random.nextInt(4) - 2, blockpos.getY(), random.nextInt(4) - 2);

                if (world.isAirBlock(pos_around) && pos_around.getY() < 255
                        && Illuminations.ObjectHolders.GRASS_BLOCK.getDefaultState().isValidPosition(world, pos_around)) {

                    if (random.nextBoolean())
                        ((DoublePlantBlock) Illuminations.ObjectHolders.TALL_GRASS_BLOCK).placeAt(world, pos_around, 2);
                    else
                        world.setBlockState(pos_around, Illuminations.ObjectHolders.TALL_GRASS_BLOCK.getDefaultState(), 2);
                    ++i;
                }
            }

        }
        return i > 0;
    }
}
