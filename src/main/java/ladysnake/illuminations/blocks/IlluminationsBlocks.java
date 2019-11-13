package ladysnake.illuminations.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.Blocks;

public class IlluminationsBlocks {

    private static Properties grass_prop = Properties.from(Blocks.GRASS).lightValue(1);

    public static FireFlyGrassBlock block = new FireFlyGrassBlock(grass_prop);
    
    public static Block[] registry() {
        return new Block[] {};
    }
}
