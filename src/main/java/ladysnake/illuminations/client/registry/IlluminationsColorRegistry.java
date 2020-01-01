package ladysnake.illuminations.client.registry;

import ladysnake.illuminations.mod.Illuminations;
import net.minecraft.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Illuminations.MODID, value = Dist.CLIENT, bus = Bus.MOD)
public class IlluminationsColorRegistry {

    @SubscribeEvent
    public static void register(ColorHandlerEvent.Block event)
    {

        event.getBlockColors().register((block, pos, world, layer) -> {
            // getColor
            int color = event.getBlockColors().func_228054_a_(Blocks.GRASS.getDefaultState(), pos, world, layer);
            return color;
        }, Illuminations.ObjectHolders.FIREFLY_GRASS_BLOCK);

        event.getBlockColors().register((block, pos, world, layer) -> {
            // getColor
            int color = event.getBlockColors().func_228054_a_(Blocks.GRASS.getDefaultState(), pos, world, layer);
            return color;
        }, Illuminations.ObjectHolders.FIREFLY_TALL_GRASS_BLOCK);
    }
}
