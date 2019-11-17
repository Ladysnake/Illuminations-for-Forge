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

    // Do not use a field reference here.
    // We're preferring object holders in case someone wants to replace our entity.
    // This is good mod compatibility practice.
    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> e) {

        EntityType<FireFlyEntity> entity_type = EntityType.Builder.create(FireFlyEntity::new, EntityClassification.AMBIENT).size(0.5f, 0.5f)
                .build("firefly_entity_type");
        entity_type.setRegistryName(new ResourceLocation(Illuminations.MODID, "firefly_entity_type"));
        e.getRegistry().register(entity_type);
    }
}
