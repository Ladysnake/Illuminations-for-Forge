package ladysnake.illuminations.registry;

import ladysnake.illuminations.blocks.IlluminationsBlocks;
import ladysnake.illuminations.item.IlluminationsItems;
import ladysnake.illuminations.mod.Illuminations;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Illuminations.MODID, bus = Bus.MOD)
public class IlluminationsItemRegistry {

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> e) {

        e.getRegistry().registerAll(IlluminationsItems.registery());
        e.getRegistry().registerAll(IlluminationsBlocks.blockItemRegistry());
    }
}
