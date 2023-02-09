package me.roundaround.autoscroll.client.event;

import java.util.Arrays;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.MinecraftClient;

@Environment(value = EnvType.CLIENT)
public interface MinecraftClientEvents {
  Event<MinecraftClientEvents> ON_INPUT = EventFactory.createArrayBacked(MinecraftClientEvents.class,
      (listeners) -> (client) -> Arrays.stream(listeners).forEach((listener) -> listener.interact(client)));
  Event<MinecraftClientEvents> ON_SCREEN_CHANGE = EventFactory.createArrayBacked(MinecraftClientEvents.class,
      (listeners) -> (client) -> Arrays.stream(listeners).forEach((listener) -> listener.interact(client)));
  Event<MinecraftClientEvents> ON_MOUSE_UPDATE = EventFactory.createArrayBacked(MinecraftClientEvents.class,
      (listeners) -> (client) -> Arrays.stream(listeners).forEach((listener) -> listener.interact(client)));

  void interact(MinecraftClient client);
}
