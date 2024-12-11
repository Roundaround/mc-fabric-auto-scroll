package me.roundaround.autoscroll.client;

import me.roundaround.autoscroll.client.event.MinecraftClientEvents;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.Scroller;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerInventory;

public class AutoScrollClientMod implements ClientModInitializer {
  public static KeyBinding activateKeyBinding;
  public static boolean isScrolling = false;

  @Override
  public void onInitializeClient() {
    activateKeyBinding = KeyBindingHelper.registerKeyBinding(
        new KeyBinding("autoscroll.key.activate", InputUtil.Type.KEYSYM, InputUtil.UNKNOWN_KEY.getCode(),
            KeyBinding.MISC_CATEGORY
        ));

    MinecraftClientEvents.ON_INPUT.register((client) -> {
      if (client.currentScreen != null) {
        return;
      }

      while (activateKeyBinding.wasPressed()) {
        isScrolling = !isScrolling;
      }
    });

    MinecraftClientEvents.ON_SCREEN_CHANGE.register((client) -> {
      isScrolling = false;
    });

    WorldRenderEvents.START.register((context) -> {
      MinecraftClient client = MinecraftClient.getInstance();

      if (!isScrolling || client.currentScreen != null || client.player == null) {
        return;
      }

      if (client.player.isSpectator()) {
        isScrolling = false;
        return;
      }

      PlayerInventory inventory = client.player.getInventory();
      inventory.setSelectedSlot(Scroller.scrollCycling(-1, inventory.selectedSlot, PlayerInventory.getHotbarSize()));
    });
  }
}
