package ladysnake.illuminations.registry;

import java.util.Arrays;
import java.util.List;

import ladysnake.illuminations.feature.GrassGenFeature;
import ladysnake.illuminations.mod.Illuminations;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Illuminations.MODID, bus = Bus.MOD)
public class IlluminationsFeatureRegistry {

    public static Feature<ProbabilityConfig> FIREFLY_PLANT;

    @SubscribeEvent
    public static void registerFeature(RegistryEvent.Register<Feature<?>> e) {

        FIREFLY_PLANT = new GrassGenFeature(ProbabilityConfig::deserialize);
        FIREFLY_PLANT.setRegistryName(Illuminations.MODID, "fire_fly_worldgen_feature");
        
        e.getRegistry().register(FIREFLY_PLANT);

        System.out.println("registered FIREFLY feature !!");
        List<Biome.Category> fireflyBiomes = Arrays.asList(Category.PLAINS, Category.SWAMP, Category.FOREST, Category.JUNGLE, Category.SAVANNA, Category.RIVER);
        for (Biome biome : Biome.BIOMES) {
            System.out.println("added feature for " + biome);
            if (fireflyBiomes.contains(biome.getCategory())) {
                biome.addFeature(Decoration.VEGETAL_DECORATION,
                        Biome.createDecoratedFeature(FIREFLY_PLANT, new ProbabilityConfig(10F), Placement.COUNT_RANGE, new CountRangeConfig(90, 0, 0, 250)));
            }
        }
    }
}
