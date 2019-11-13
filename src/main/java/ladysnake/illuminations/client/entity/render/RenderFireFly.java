package ladysnake.illuminations.client.entity.render;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;

import ladysnake.illuminations.entity.FireFlyEntity;
import ladysnake.illuminations.mod.Illuminations;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class RenderFireFly<E extends FireFlyEntity> extends EntityRenderer<E> {

    private static final ResourceLocation resLoc = new ResourceLocation(Illuminations.MODID, "textures/entity/firefly.png");

    protected RenderFireFly(EntityRendererManager renderManager) {

        super(renderManager);
    }

    @Override
    public void doRender(E entity, double x, double y, double z, float entityYaw, float partialTicks) {

        if (!this.renderOutlines) {
            GlStateManager.pushMatrix();

            GlStateManager.translatef((float) x, (float) y + 0.1f, (float) z);

            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            GlStateManager.disableLighting();

            GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, 240f, 240f);

            GlStateManager.rotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotatef((float) (this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * -this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);

            this.bindEntityTexture(entity);
            float alpha = entity.getAlpha();
            float scale = entity.getScaleModifier();
            float color = entity.getColorModifier();
            Float nextAlphaGoal = entity.getNextAlphaGoal();

            // if just spawned
            if (entity.ticksExisted < 50) {
                alpha = 0;
            }

            // if day
            float tod = entity.world.getWorldInfo().getDayTime();
            if (tod >= 1000 && tod < 13000) {
                nextAlphaGoal = 0.0F;
            }

            if (nextAlphaGoal == null || nextAlphaGoal.equals(round(alpha, 1))) {
                entity.setNextAlphaGoal(new Random().nextInt(11) / 10.0F);
            } else {
                if (nextAlphaGoal > alpha) {
                    alpha += 0.05F;
                } else if (nextAlphaGoal < alpha) {
                    alpha -= 0.05F;
                }
            }

            entity.setAlpha(Math.min(Math.max(alpha, 0), 1));
            GlStateManager.scalef(scale, scale, scale);
            GlStateManager.color4f(color, 1F, 0F, entity.getAlpha());

            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferbuilder = tessellator.getBuffer();
            float minU = 0;
            float minV = 0;
            float maxU = 1;
            float maxV = 1;
            bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_NORMAL);
            bufferbuilder.pos(-0.5D, -0.25D, 0.0D).tex((double) maxU, (double) maxV).normal(0.0F, 1.0F, 0.0F).endVertex();
            bufferbuilder.pos(0.5D, -0.25D, 0.0D).tex((double) minU, (double) maxV).normal(0.0F, 1.0F, 0.0F).endVertex();
            bufferbuilder.pos(0.5D, 0.75D, 0.0D).tex((double) minU, (double) minV).normal(0.0F, 1.0F, 0.0F).endVertex();
            bufferbuilder.pos(-0.5D, 0.75D, 0.0D).tex((double) maxU, (double) minV).normal(0.0F, 1.0F, 0.0F).endVertex();
            tessellator.draw();

            this.bindTexture(getEntityTexture(entity));

            GlStateManager.color4f(1F, 1F, 1F, entity.getAlpha());
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_NORMAL);
            bufferbuilder.pos(-0.5D, -0.25D, 0.0D).tex((double) maxU, (double) maxV).normal(0.0F, 1.0F, 0.0F).endVertex();
            bufferbuilder.pos(0.5D, -0.25D, 0.0D).tex((double) minU, (double) maxV).normal(0.0F, 1.0F, 0.0F).endVertex();
            bufferbuilder.pos(0.5D, 0.75D, 0.0D).tex((double) minU, (double) minV).normal(0.0F, 1.0F, 0.0F).endVertex();
            bufferbuilder.pos(-0.5D, 0.75D, 0.0D).tex((double) maxU, (double) minV).normal(0.0F, 1.0F, 0.0F).endVertex();
            tessellator.draw();

            GlStateManager.disableBlend();
            GlStateManager.disableRescaleNormal();
            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
            super.doRender(entity, x, y, z, entityYaw, partialTicks);
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(E entity) {

        return resLoc;
    }

    /**rounds a number to the' precision value' + 1 after the comma, rather then the first value after the comma*/
    private static float round(float value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (float) Math.round(value * scale) / scale;
    }
}
