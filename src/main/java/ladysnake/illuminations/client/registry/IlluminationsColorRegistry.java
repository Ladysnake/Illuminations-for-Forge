package ladysnake.illuminations.client.registry;

import ladysnake.illuminations.blocks.IlluminationsBlocks;
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
    public static void register(ColorHandlerEvent.Block event) {

        event.getBlockColors().register((block, pos, world, layer) -> {
            int color = event.getBlockColors().getColor(Blocks.GRASS.getDefaultState(), pos, world, layer);
            return color;
        }, IlluminationsBlocks.grass);

        event.getBlockColors().register((block, pos, world, layer) -> {
            int color = event.getBlockColors().getColor(Blocks.GRASS.getDefaultState(), pos, world, layer);
            return color;
        }, IlluminationsBlocks.tall_grass);
    }
}
