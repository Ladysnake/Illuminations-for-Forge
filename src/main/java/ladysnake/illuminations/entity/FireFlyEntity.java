package ladysnake.illuminations.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public class FireFlyEntity extends LightOrbEntity {

    protected float scaleModifier;
    protected float colorModifier;
    protected float alphaModifier;
    protected boolean canDespawn;
    protected boolean isAttractedByLight;
    private Float nextAlphaGoal;

    public FireFlyEntity(EntityType<? extends MobEntity> type, World world) {

        super(type, world);
    }

    public float getScaleModifier() {

        return scaleModifier;
    }

    public float getColorModifier() {

        return colorModifier;
    }

    public float getAlpha() {

        return alphaModifier;
    }

    public void setAlpha(float alpha) {

        this.alphaModifier = alpha;
    }

    public Float getNextAlphaGoal() {

        return nextAlphaGoal;
    }

    public void setNextAlphaGoal(Float nextAlphaGoal) {

        this.nextAlphaGoal = nextAlphaGoal;
    }

    public boolean isAttractedByLight() {

        return isAttractedByLight;
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {

        compound.putFloat("scale", scaleModifier);
        compound.putFloat("color", colorModifier);
        compound.putFloat("alpha", alphaModifier);
        compound.putBoolean("atracted", isAttractedByLight);

        super.writeAdditional(compound);
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        
        scaleModifier = compound.getFloat("scale");
        colorModifier = compound.getFloat("color");
        alphaModifier = compound.getFloat("alpha");

        super.readAdditional(compound);
    }

}
