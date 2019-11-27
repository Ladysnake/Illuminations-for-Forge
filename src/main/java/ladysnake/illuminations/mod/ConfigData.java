package ladysnake.illuminations.mod;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigData {

    public static final ServerConfig SERVER;
    public static final ForgeConfigSpec SERVER_SPEC;

    static {
        final Pair<ServerConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
        SERVER_SPEC = specPair.getRight();
        SERVER = specPair.getLeft();
    }

    public static final ClientConfig CLIENT;
    public static final ForgeConfigSpec CLIENT_SPEC;

    static {
        final Pair<ClientConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
        CLIENT_SPEC = specPair.getRight();
        CLIENT = specPair.getLeft();
    }

    // max flies to check before stopping spawning
    public static int max_flies_near_block = 26;
    // in chunks
    public static int start_spawning_flies = 1;
    // in blocks
    public static int max_flies_radius_near_block = 16;
    public static int rarity = 4;

    // in chunks
    public static int despawn_firefly = 10;

    // client : in chunks
    public static int render_range = 10;

    public static class ServerConfig {

        public final ForgeConfigSpec.IntValue max_flies_near_block;
        public final ForgeConfigSpec.IntValue start_spawning_flies;
        public final ForgeConfigSpec.IntValue max_flies_radius_near_block;
        public final ForgeConfigSpec.IntValue despawn_firefly;
//        public final ForgeConfigSpec.IntValue rarity;

        ServerConfig(ForgeConfigSpec.Builder builder) {

            builder.push("Firefly Grass");
            
            max_flies_near_block = builder.comment("How many fireflies need to be near a firefly grass block for it to stop spawning them")
                    .translation("translate.").defineInRange("max_flies_near_block", 26, 6, 100);

            max_flies_radius_near_block = builder.comment("The block radius in which max_flies_near_block is checked").translation("translate.")
                    .defineInRange("max_flies_radius_near_block", 16, 1, 128);

            start_spawning_flies = builder.comment("How close by a player needs to be for fireflies to start spawning (value in chunks)")
                    .translation("translate.").defineInRange("start_spawning_flies", 1, 1, 32);

//            rarity = builder.comment("How rare the grass spawning is. Higher number means less rare").translation("translate.").defineInRange("rarity", 4, 1,
//                    64);
            builder.pop();

            builder.push("Firefly Entity");

            despawn_firefly = builder.comment("How far away a player has to be for fireflies to despawn (value in chunks)").translation("translate.")
                    .defineInRange("despawn_firefly", 1, 1, 32);

            builder.pop();

        }
    }

    public static class ClientConfig {

        public final ForgeConfigSpec.IntValue render_range;

        ClientConfig(ForgeConfigSpec.Builder builder) {

            builder.push("Firefly Entity");
            
            render_range = builder.comment(String.format(
                    "After How many chunks fireflies need to stop rendering. (The entities will still exist , you just wont see them) \nThis is a good performance tweak when playing on a server that has set a high firefly count, and you run minecraft on a toaster."))
                    .translation("translate.").defineInRange("render_range", 1, 1, 32);
            
            builder.pop();

        }
    }

    public static void refreshClient() {

        //value is given in chunks, so we convert back to blocks
        render_range = CLIENT.render_range.get() * 16;
    }

    public static void refreshServer() {

        max_flies_near_block = SERVER.max_flies_near_block.get();
        max_flies_radius_near_block = SERVER.max_flies_radius_near_block.get();
        //rarity = SERVER.rarity.get();

        //value is given in chunks, so we convert back to blocks
        despawn_firefly = SERVER.despawn_firefly.get() * 16;
        start_spawning_flies = SERVER.start_spawning_flies.get() * 16;
    }
}
