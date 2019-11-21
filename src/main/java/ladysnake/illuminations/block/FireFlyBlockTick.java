package ladysnake.illuminations.block;

import java.util.List;
import java.util.Random;

import ladysnake.illuminations.entity.FireFlyEntity;
import ladysnake.illuminations.mod.ConfigData;
import ladysnake.illuminations.mod.Illuminations;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FireFlyBlockTick {

    public static void tick(BlockState state, World world, BlockPos pos, Random random) {

        // only ever spawn entities on server (!isRemote)
        // only spawn fire flies at night
        if (!world.isRemote && !world.isDaytime()) {

            // Start spawning fire flies when the player is in chunk distance (default 16
            // blocks)
            boolean anyPlayerCloseBy = world.isPlayerWithin(pos.getX(), pos.getY(), pos.getZ(), ConfigData.start_spawning_flies);

            // Get all fire flies in a radius around the given block. we grow it's Bounding
            // Box by 16 on both neg and pos sides, so 32 total
            int rad = ConfigData.max_flies_radius_near_block;
            List<FireFlyEntity> firefliesInRadius = world.getEntitiesWithinAABB(FireFlyEntity.class,
                    new AxisAlignedBB(pos).grow(rad, rad, rad));

            // spawn one fire fly when a player is near and the nearby fire flies are less
            // then the given max
            if (anyPlayerCloseBy && firefliesInRadius.size() < ConfigData.max_flies_near_block) {

                FireFlyEntity firefly = new FireFlyEntity(Illuminations.ObjectHolders.FIREFLY_ENTITY_TYPE, world);
                firefly.setPosition(pos.getX() + random.nextDouble(), pos.getY(), pos.getZ() + random.nextDouble());
                world.addEntity(firefly);
            }
        }
    }
}
