package ladysnake.illuminations.mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod(Illuminations.MODID)
@EventBusSubscriber(modid = Illuminations.MODID, bus = Bus.MOD)
public class Illuminations {

    public static final String MODID = "illuminations";

    public static Logger LOG = LogManager.getLogger(MODID);

}
