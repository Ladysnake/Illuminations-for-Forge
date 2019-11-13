package ladysnake.illuminations.mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ladysnake.illuminations.item.IlluminationItems;
import ladysnake.illuminations.registry.IlluminationsRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Illuminations.MODID)
@EventBusSubscriber(modid = Illuminations.MODID, bus = Bus.MOD)

public class Illuminations {

    public static final String MODID = "illuminations";

    public static Logger LOG = LogManager.getLogger(MODID);

    public Illuminations() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

        IlluminationsRegistry.registerAll();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

}
