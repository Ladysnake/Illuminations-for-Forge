package ladysnake.illuminations.blocks;

import java.util.List;
import java.util.Random;

import ladysnake.illuminations.entity.FireFlyEntity;
import ladysnake.illuminations.registry.IlluminationsEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FireFlyBlockTick {

    public static final int MIN_FIREFLIES = 6;
    public static final int MAX_FIREFLIES = 24;
    
    public static void tick(BlockState state, World world, BlockPos pos, Random random) {

        if (!world.isRemote && !world.isDaytime()) {
            //spawn when in chunk > radius is 16
            boolean anyPlayerCloseBy = world.isPlayerWithin(pos.getX(), pos.getY(), pos.getZ(), 16);
            // grow 16 on both neg and pos sides, so 32 total
            List<FireFlyEntity> firefliesInRadius = world.getEntitiesWithinAABB(FireFlyEntity.class, new AxisAlignedBB(pos).grow(16, 16, 16));

            if (anyPlayerCloseBy) {

                int toSpawn = 0;

                if (firefliesInRadius.size() < MAX_FIREFLIES)
                    toSpawn = 1;

                if (toSpawn > 0) {
                    for (int i = 0; i < toSpawn; i++) {
                        FireFlyEntity firefly = new FireFlyEntity(IlluminationsEntityRegistry.firefly_entity_type, world);
                        firefly.setPosition(pos.getX() + random.nextDouble(), pos.getY(), pos.getZ() + random.nextDouble());
                        world.addEntity(firefly);
                    }
                }
            }
        }
    }
}
