package ladysnake.illuminations.registry;

import ladysnake.illuminations.entity.FireFlyEntity;
import ladysnake.illuminations.mod.Illuminations;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class IlluminationsEntityRegistry {

    public IlluminationsEntityRegistry() {

        MinecraftForge.EVENT_BUS.register(this);
    }
    
    EntityType<FireFlyEntity> type = EntityType.Builder.<FireFlyEntity>create(FireFlyEntity::new, EntityClassification.AMBIENT).size(0.5f, 0.5f).build(new ResourceLocation(Illuminations.MODID,"fire_fly").toString());

    @SubscribeEvent
    public void registerEntities(RegistryEvent.Register<EntityType<?>> e) {
        e.getRegistry().register(type);
    }
}
