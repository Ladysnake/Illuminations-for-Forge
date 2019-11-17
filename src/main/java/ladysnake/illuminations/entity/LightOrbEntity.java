package ladysnake.illuminations.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class LightOrbEntity extends MobEntity {

    protected LightOrbEntity(EntityType<? extends MobEntity> type, World worldIn) {

        super(type, worldIn);
    }

    // entity can 'fly'
    @Override
    public boolean hasNoGravity() {

        return true;
    }

    // no logic when falling
    @Override
    public void fall(float distance, float damageMultiplier) {

    }

    // no hurt sound
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {

        return null;
    }

    // no death sound
    @Override
    protected SoundEvent getDeathSound() {

        return null;
    }

    // handles particle spawn on death.
    @Override
    protected void onDeathUpdate() {

    }

    // No colliding or pushing players or other mobs
    @Override
    public void applyEntityCollision(Entity entityIn) {

    }

    // No colliding or pushing players or other mobs
    @Override
    protected void collideWithEntity(Entity entityIn) {

    }

    // no leashing
    @Override
    public boolean canBeLeashedTo(PlayerEntity player) {

        return false;
    }

    @Override
    public boolean doesEntityNotTriggerPressurePlate() {

        return true;
    }

    @Override
    public boolean isOnLadder() {

        return false;
    }

}
