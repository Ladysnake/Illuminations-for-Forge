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

    @SubscribeEvent
    public static void registerFeature(RegistryEvent.Register<Feature<?>> e) {

        // Make feature
        Feature<ProbabilityConfig> FIREFLY_PLANT = new GrassGenFeature(ProbabilityConfig::deserialize);
        FIREFLY_PLANT.setRegistryName(Illuminations.MODID, "fire_fly_worldgen_feature");

        // Register feature
        e.getRegistry().register(FIREFLY_PLANT);

        // make list of biomes in which feature should naturally spawn
        List<Biome.Category> fireflyBiomes = Arrays.asList(Category.PLAINS, Category.SWAMP, Category.FOREST, Category.JUNGLE, Category.SAVANNA, Category.RIVER);

        // Add feature to every biome we defined to be generated
        for (Biome biome : Biome.BIOMES) {
            if (fireflyBiomes.contains(biome.getCategory())) {
                biome.addFeature(Decoration.VEGETAL_DECORATION,
                        Biome.createDecoratedFeature(FIREFLY_PLANT, new ProbabilityConfig(10F), Placement.COUNT_RANGE, new CountRangeConfig(90, 0, 0, 250)));
            }
        }
    }
}
