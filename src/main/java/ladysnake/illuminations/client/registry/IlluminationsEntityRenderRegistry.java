package ladysnake.illuminations.client.registry;

import ladysnake.illuminations.client.entity.render.RenderFireFly;
import ladysnake.illuminations.entity.FireFlyEntity;
import ladysnake.illuminations.mod.Illuminations;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = Illuminations.MODID, value = Dist.CLIENT, bus = Bus.MOD)
public class IlluminationsEntityRenderRegistry {

    @SubscribeEvent
    public static void doClientStuff(final FMLClientSetupEvent event) {

        RenderingRegistry.registerEntityRenderingHandler(FireFlyEntity.class, RenderFireFly::new);

    }
}
