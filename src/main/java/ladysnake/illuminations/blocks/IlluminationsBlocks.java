package ladysnake.illuminations.blocks;

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

    private static Properties grass_prop = PropertiesWrapper.fromBlockProperty(Blocks.GRASS).lightValue(1);

    public static FireFlyGrassBlock grass = new FireFlyGrassBlock(grass_prop);
    public static FireFlyTallGrassBlock tall_grass = new FireFlyTallGrassBlock(grass_prop);

    //no need to access this at any point. hence they are private
    private static BlockItem grass_item = new BlockItem(grass, PropertiesWrapper.getItemProperties().group(ItemGroup.DECORATIONS));
    private static BlockItem tall_grass_item = new BlockItem(tall_grass, PropertiesWrapper.getItemProperties().group(ItemGroup.DECORATIONS));

    public static Block[] registry() {

        grass.setRegistryName(new ResourceLocation(Illuminations.MODID, "firefly_grass"));
        tall_grass.setRegistryName(new ResourceLocation(Illuminations.MODID, "firefly_tall_grass"));

        return new Block[] { grass, tall_grass };
    }

    public static Item[] blockItemRegistry() {

        grass_item.setRegistryName(grass.getRegistryName());
        tall_grass_item.setRegistryName(tall_grass.getRegistryName());

        return new BlockItem[] { grass_item, tall_grass_item };
    }
}
