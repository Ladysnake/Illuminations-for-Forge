package ladysnake.illuminations.block;

import ladysnake.illuminations.mod.Illuminations;
import ladysnake.illuminations.util.PropertiesWrapper;
import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;

public class IlluminationsBlocks {

    private static Properties grass_prop = PropertiesWrapper.fromBlockProperty(Blocks.GRASS).lightValue(16);

    /** Should only be called when initializing Blocks in the Registry event */
    public static Block[] registry() {

        FireFlyGrassBlock grass = new FireFlyGrassBlock(grass_prop);
        grass.setRegistryName(new ResourceLocation(Illuminations.MODID, "firefly_grass"));

        FireFlyTallGrassBlock tall_grass = new FireFlyTallGrassBlock(grass_prop);
        tall_grass.setRegistryName(new ResourceLocation(Illuminations.MODID, "firefly_tall_grass"));

        return new Block[] { grass, tall_grass };
    }

    /** Should only be called when initializing Items in the Registry event */
    public static Item[] blockItemRegistry() {

        BlockItem grass_item = new BlockItem(Illuminations.ObjectHolders.FIREFLY_GRASS_BLOCK, PropertiesWrapper.getItemProperties().group(ItemGroup.DECORATIONS));
        grass_item.setRegistryName(Illuminations.ObjectHolders.FIREFLY_GRASS_BLOCK.getRegistryName());

        BlockItem tall_grass_item = new BlockItem(Illuminations.ObjectHolders.FIREFLY_TALL_GRASS_BLOCK, PropertiesWrapper.getItemProperties().group(ItemGroup.DECORATIONS));
        tall_grass_item.setRegistryName(Illuminations.ObjectHolders.FIREFLY_TALL_GRASS_BLOCK.getRegistryName());

        return new BlockItem[] { grass_item, tall_grass_item };
    }
}
