package me.roundaround.autoscroll.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.roundaround.autoscroll.client.event.MinecraftClientEvents;
import net.minecraft.client.MinecraftClient;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
  @Inject(method = "handleInputEvents", at = @At(value = "HEAD"))
  public void handleInputEvents(CallbackInfo info) {
    MinecraftClientEvents.ON_INPUT.invoker().interact((MinecraftClient) (Object) this);
  }

  @Inject(method = "setScreen", at = @At(value = "HEAD"))
  public void setScreen(CallbackInfo info) {
    MinecraftClientEvents.ON_SCREEN_CHANGE.invoker().interact((MinecraftClient) (Object) this);
  }
}
