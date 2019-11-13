package ladysnake.illuminations.registry;

import ladysnake.illuminations.item.IlluminationsItems;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

public class IlluminationsItemRegistry {

    public static void registerItems(RegistryEvent.Register<Item> e) {

        IlluminationsItems.init();
        e.getRegistry().registerAll(IlluminationsItems.registery());
    }
}
