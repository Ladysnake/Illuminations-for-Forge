package ladysnake.illuminations.registry;

import ladysnake.illuminations.item.IlluminationsItems;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;

public class IlluminationsBlockRegistry {

    public static void registerBlocks(RegistryEvent.Register<Block> e) {

        IlluminationsItems.init();
        e.getRegistry().registerAll(IlluminationsBlocks.registery());
    }
}
