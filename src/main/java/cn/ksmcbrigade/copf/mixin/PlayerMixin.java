package cn.ksmcbrigade.copf.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {
    protected PlayerMixin(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_);
    }

    @Shadow public abstract boolean isCreative();

    @ModifyArg(method = "attack",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"),index = 1)
    public float attack(float value){
        if(this.isCreative() && this.getItemInHand(this.getUsedItemHand()).isEmpty()){
            return Float.MAX_VALUE;
        }
        return value;
    }

    @ModifyArg(method = "attack",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"),index = 1)
    public float attack2(float value){
        if(this.isCreative() && this.getItemInHand(this.getUsedItemHand()).isEmpty()){
            return Float.MAX_VALUE;
        }
        return value;
    }
}
