package ladysnake.illuminations.item;

import ladysnake.illuminations.entity.FireFlyEntity;
import ladysnake.illuminations.mod.Illuminations;
import ladysnake.illuminations.registry.IlluminationsEntityRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;

public class IlluminationsItems {

    private static Properties bugnet_prop = new Properties().defaultMaxDamage(238).group(ItemGroup.TOOLS);
    private static Properties firefly_prop = new Properties().group(ItemGroup.MISC);

    public static Item bugnet, firefly;

    public static void init() {

        bugnet = new Item(bugnet_prop) {

            @Override
            public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {

                if (!(attacker instanceof PlayerEntity))
                    return false;
                if (!(target instanceof FireFlyEntity))
                    return false;

                target.remove();

                PlayerEntity player = (PlayerEntity) attacker;
                stack.damageItem(1, player, p -> {
                    p.setHeldItem(player.getActiveHand(), ItemStack.EMPTY);
                });
                return true;
            };
        }.setRegistryName(Illuminations.MODID, "bug_net");

        firefly = new Item(firefly_prop) {

            @Override
            public ActionResultType onItemUse(ItemUseContext context) {

                if(!context.getWorld().isRemote)
                {
                    BlockPos pos = context.getPos();
                    FireFlyEntity firefly = new FireFlyEntity(IlluminationsEntityRegistry.firefly_entity_type, context.getWorld());
                    firefly.setPosition(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
                    context.getWorld().addEntity(firefly);

                    if (!context.getPlayer().abilities.isCreativeMode)
                        context.getItem().shrink(1); 
                }
               

                return ActionResultType.SUCCESS;
            };
        }.setRegistryName(Illuminations.MODID, "firefly");
    }

    public static Item[] registery() {

        return new Item[] { bugnet, firefly };
    }
}
