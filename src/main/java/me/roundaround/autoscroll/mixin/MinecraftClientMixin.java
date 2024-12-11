package me.roundaround.autoscroll.mixin;

import me.roundaround.autoscroll.client.event.MinecraftClientEvents;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
  @Inject(method = "handleInputEvents", at = @At(value = "HEAD"))
  public void handleInputEvents(CallbackInfo info) {
    MinecraftClientEvents.ON_INPUT.invoker().interact(this.self());
  }

  @Inject(method = "setScreen", at = @At(value = "HEAD"))
  public void setScreen(CallbackInfo info) {
    MinecraftClientEvents.ON_SCREEN_CHANGE.invoker().interact(this.self());
  }

  @Unique
  private MinecraftClient self() {
    return (MinecraftClient) (Object) this;
  }
}
