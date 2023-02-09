package me.roundaround.autoscroll.client;

import org.lwjgl.glfw.GLFW;

import me.roundaround.autoscroll.client.event.MinecraftClientEvents;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class AutoScrollClientMod implements ClientModInitializer {
  public static KeyBinding activateKeyBinding;
  public static boolean isScrolling = false;

  @Override
  public void onInitializeClient() {
    activateKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
        "autoscroll.key.activate",
        InputUtil.Type.KEYSYM,
        GLFW.GLFW_KEY_O,
        KeyBinding.MISC_CATEGORY));

    MinecraftClientEvents.ON_INPUT.register((client) -> {
      if (activateKeyBinding.wasPressed() && client.currentScreen == null) {
        isScrolling = !isScrolling;

        // Drain any remaining events from the queue so we only fire once per press
        while (activateKeyBinding.wasPressed()) {
        }
      }
    });

    MinecraftClientEvents.ON_SCREEN_CHANGE.register((client) -> {
      isScrolling = false;
    });

    ClientTickEvents.END_CLIENT_TICK.register((client) -> {
      if (isScrolling && client.currentScreen == null && client.player != null) {
        client.player.getInventory().scrollInHotbar(-1);
      }
    });
  }
}
