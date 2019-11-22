package ladysnake.illuminations.feature;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Function;

import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;

import ladysnake.illuminations.mod.ConfigData;
import ladysnake.illuminations.mod.Illuminations.ObjectHolders;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class FireflyGrassGenFeature extends Feature<ProbabilityConfig> {

    public FireflyGrassGenFeature(Function<Dynamic<?>, ? extends ProbabilityConfig> configFactoryIn) {

        super(configFactoryIn);
    }

    @Override
    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random random, BlockPos pos, ProbabilityConfig config) {

        int i = 0;

        ArrayList<BlockPos> grass_batch = Lists.newArrayList();

        // number of blocks in which grass will have a chance to generate around the center block
        double reserved_generation_space = random.nextInt(10)+6;
        double reserved_generation_space_squared = reserved_generation_space * reserved_generation_space;
        
        double proportional_grass_size_per_batch = reserved_generation_space_squared * 0.45;
        
        double guaranteed_grass_size = proportional_grass_size_per_batch * 0.625;
        double random_grass_size = proportional_grass_size_per_batch - guaranteed_grass_size;
        
        int batch_size = random.nextInt((int)random_grass_size) + (int)guaranteed_grass_size;

        for (int rarity = 0; rarity < ConfigData.rarity; ++rarity) {

            if (random.nextInt(4) > 0)
                continue;

            // check a random nearby position from the given position 'pos' if its air. if
            // not, skip.
            // this enhances the probability of not spawning grass
            BlockPos blockpos = pos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));

            if (!world.isAirBlock(blockpos))
                continue;

            // check to see if the random chosen block can hold our grass. if not, skip.
            // this enhances the probability of not spawning grass
            if (!ObjectHolders.FIREFLY_GRASS_BLOCK.getDefaultState().isValidPosition(world, blockpos))
                continue;

            while (grass_batch.size() < batch_size) {

                // square of X*X, random position generated
                int x = random.nextInt((int)reserved_generation_space) - (int)(reserved_generation_space / 2D);
                int z = random.nextInt((int)reserved_generation_space) - (int)(reserved_generation_space / 2D);

                int blockPosX = blockpos.getX() + x;
                int blockPosZ = blockpos.getZ() + z;

                // select a random position near the center pos
                // note : use WORLD_SURFACE_WG (WG stands for world gen) to get the correct
                // world height, ignoring any biome specific generation like grass or flowers
                BlockPos heightFix = new BlockPos(blockPosX, world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, blockPosX, blockPosZ), blockPosZ);

                // if the position was already added, re iterate and make a new one until we get
                // batch_size number of unique positions
                if (!grass_batch.contains(heightFix))
                    grass_batch.add(heightFix);

            }

            // Iterate over all generated block positions
            for (BlockPos to_gen_pos : grass_batch) {

                // if the position is below build height and grass can be placed here (same
                // logic as minecraft bush blocks)
                if (to_gen_pos.getY() < 255 && ObjectHolders.FIREFLY_GRASS_BLOCK.getDefaultState().isValidPosition(world, to_gen_pos)) {

                    // one chance in two to spawn big grass
                    if (random.nextBoolean())
                        ((DoublePlantBlock) ObjectHolders.FIREFLY_TALL_GRASS_BLOCK).placeAt(world, to_gen_pos, 2);
                    else
                        world.setBlockState(to_gen_pos, ObjectHolders.FIREFLY_GRASS_BLOCK.getDefaultState(), 2);
                    ++i;
                }
            }
        }
        return i > 0;
    }
}
