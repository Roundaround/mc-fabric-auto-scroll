package me.roundaround.autoscroll.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.roundaround.autoscroll.client.AutoScrollClientMod;
import net.minecraft.client.Mouse;

@Mixin(Mouse.class)
public abstract class MouseMixin {
  @Inject(method = "onMouseScroll", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;scrollInHotbar(D)V"), cancellable = true)
  private void onMouseScroll(long window, double horizontal, double vertical, CallbackInfo info) {
    if (AutoScrollClientMod.isScrolling) {
      info.cancel();
    }
  }
}
