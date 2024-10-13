package com.saahild;

import com.saahild.command.SetAPIKey;
import com.saahild.event.WakatimePlayerMoveEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.server.PlayerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WakatimeMod implements ModInitializer {

  public static final String MOD_ID = "wakatime-mod";

  // This logger is used to write text to the console and the log file.
  // It is considered best practice to use your mod id as the logger's name.
  // That way, it's clear which mod wrote info, warnings, and errors.
  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

  @Override
  public void onInitialize() {
    // This code runs as soon as Minecraft is in a mod-load-ready state.
    // However, some things (like resources) may still be uninitialized.
    // Proceed with mild caution.

    LOGGER.info("Setting up commands & events");
    registerCommands();
    registerEvents();
    LOGGER.info("Set up commands & events!");
  }

  private static void registerCommands() {
    CommandRegistrationCallback.EVENT.register(SetAPIKey::register);
  }

  // register events
  private static void registerEvents() {
    PlayerBlockBreakEvents.AFTER.register(new WakatimePlayerMoveEvent());
  }
}
