package ladysnake.illuminations.mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ladysnake.illuminations.entity.FireFlyEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.registries.ObjectHolder;

@Mod(Illuminations.MODID)
@EventBusSubscriber(modid = Illuminations.MODID, bus = Bus.MOD)
public class Illuminations {

    public static final String MODID = "illuminations";

    public static Logger LOG = LogManager.getLogger(MODID);

    public Illuminations() {

        ModLoadingContext modLoadingContext = ModLoadingContext.get();
        modLoadingContext.registerConfig(ModConfig.Type.SERVER, ConfigData.SERVER_SPEC);
        modLoadingContext.registerConfig(ModConfig.Type.CLIENT, ConfigData.CLIENT_SPEC);
    }

    // NOTE ON OBJECT HOLDERS
    // Blocks are registered before items
    // items will be null at block registry
    // Block Items (items) can however be correctly initialized using the object
    // holders, given Blocks are registered first

    @ObjectHolder(Illuminations.MODID)
    public static class ObjectHolders {

        @ObjectHolder("firefly_entity_type")
        public static final EntityType<FireFlyEntity> FIREFLY_ENTITY_TYPE = null;

        @ObjectHolder("firefly_grass")
        public static final Block FIREFLY_GRASS_BLOCK = null;

        @ObjectHolder("firefly_tall_grass")
        public static final Block FIREFLY_TALL_GRASS_BLOCK = null;

        @ObjectHolder("bug_net")
        public static final Item BUG_NET_ITEM = null;

        @ObjectHolder("firefly")
        public static final Item FIREFLY_ITEM = null;
    }
}
