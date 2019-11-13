package ladysnake.illuminations.registry;

import ladysnake.illuminations.item.IlluminationItems;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class IlluminationsItemRegistry {

    public IlluminationsItemRegistry() {

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> e) {

        e.getRegistry().registerAll(IlluminationItems.register());

    }
}
