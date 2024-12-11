package me.roundaround.autoscroll.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import me.roundaround.autoscroll.client.AutoScrollClientMod;
import net.minecraft.client.Mouse;
import net.minecraft.entity.player.PlayerInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Mouse.class)
public abstract class MouseMixin {
  @WrapWithCondition(
      method = "onMouseScroll",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;setSelectedSlot(I)V")
  )
  private boolean shouldScrollInventory(PlayerInventory instance, int slot) {
    return !AutoScrollClientMod.isScrolling;
  }
}
