package ladysnake.illuminations.registry;

import ladysnake.illuminations.entity.FireFlyEntity;
import ladysnake.illuminations.mod.Illuminations;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Illuminations.MODID, bus = Bus.MOD)
public class IlluminationsEntityRegistry {

    public static EntityType<FireFlyEntity> firefly_entity_type = EntityType.Builder.<FireFlyEntity>create(FireFlyEntity::new, EntityClassification.AMBIENT)
            .size(0.5f, 0.5f).build(new ResourceLocation(Illuminations.MODID, "fire_fly").toString());

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> e) {

        firefly_entity_type.setRegistryName(new ResourceLocation(Illuminations.MODID, "firefly_entity_type"));

        e.getRegistry().register(firefly_entity_type);
    }
}
