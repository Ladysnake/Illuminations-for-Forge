package ladysnake.illuminations.client.entity.render;

import java.util.Random;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;

import ladysnake.illuminations.entity.FireFlyEntity;
import ladysnake.illuminations.mod.Illuminations;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class RenderFireFly<E extends FireFlyEntity> extends EntityRenderer<E> {

    private static final ResourceLocation tex = new ResourceLocation(Illuminations.MODID, "textures/entity/firefly.png");
    private static final ResourceLocation tex_overlay = new ResourceLocation(Illuminations.MODID, "textures/entity/firefly_overlay.png");

    public RenderFireFly(EntityRendererManager renderManager) {

        super(renderManager);
        this.shadowSize = 0;
    }

    ///render code mostly stolen from exp orb renderer
    @Override
    public void doRender(E entity, double x, double y, double z, float entityYaw, float partialTicks) {

        if (!this.renderOutlines) {

            GlStateManager.pushMatrix();
            GlStateManager.translatef((float)x, (float)y, (float)z);
            this.bindEntityTexture(entity);
            
            //added blend on top for smooth flickering
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            ///
            
            RenderHelper.enableStandardItemLighting();
            
            //relative luminosity in the world.
            //non calculated : always be luminous (240 is max)
            GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, 240, 240);
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
           
            GlStateManager.translatef(0.0F, 0.1F, 0.0F);
            
            GlStateManager.rotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotatef((float)(this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * -this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
            
            ///added entity logic
            float alpha = entity.getAlpha();
            float scale = entity.getScaleModifier();
            float color = entity.getColorModifier();
            Float nextAlphaGoal = entity.getNextAlphaGoal();
            
            // if just spawned
            if (entity.ticksExisted < 10) {
                alpha = 0;
            }

            //pause flickering animation if game is paused
            if(!Minecraft.getInstance().isGamePaused())
            {
                // if day
                float tod = entity.world.getWorldInfo().getDayTime();
                //why even set this if fireflies die when it is day ?
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
            }

            entity.setAlpha(Math.min(Math.max(alpha, 0), 1));
            ///
            
            GlStateManager.scalef(scale, scale, scale);
            
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferbuilder = tessellator.getBuffer();

            //we could have one for each U and V, but both are the same, so we slapped them into one min and one max
            float min = 0;
            float max = 1;
            
            for(int i = 0 ; i < 2 ; i++)
            {
                if(i == 1)
                {
                    this.bindTexture(tex_overlay);
                    GlStateManager.color4f(1F, 1F, 1F, entity.getAlpha());
                }
                else
                    GlStateManager.color4f(color, 1F, 0F, entity.getAlpha());
                
                bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
                bufferbuilder.pos(-0.5D, -0.25D, 0.0D).tex((double)max, (double)max).normal(0.0F, 1.0F, 0.0F).endVertex();
                bufferbuilder.pos(0.5D, -0.25D, 0.0D).tex((double)min, (double)max).normal(0.0F, 1.0F, 0.0F).endVertex();
                bufferbuilder.pos(0.5D, 0.75D, 0.0D).tex((double)min, (double)min).normal(0.0F, 1.0F, 0.0F).endVertex();
                bufferbuilder.pos(-0.5D, 0.75D, 0.0D).tex((double)max, (double)min).normal(0.0F, 1.0F, 0.0F).endVertex();
                tessellator.draw();
            }
            
            GlStateManager.disableBlend();
            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();
            
            super.doRender(entity, x, y, z, entityYaw, partialTicks);
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(E entity) {

        return tex;
    }

    /**rounds a number to the' precision value' + 1 after the comma, rather then the first value after the comma*/
    private static float round(float value, int precision) {
        
        int scale = (int) Math.pow(10, precision);
        
        return (float) Math.round(value * scale) / scale;
    }
}
