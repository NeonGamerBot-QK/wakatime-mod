package com.saahild.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.saahild.ConfigFile;
import com.saahild.WakatimeMod;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class SetAPIKey {

  public static void register(
      CommandDispatcher<ServerCommandSource> dispatcher,
      CommandRegistryAccess commandRegistryAccess,
      CommandManager.RegistrationEnvironment registrationEnvironment) {
    dispatcher.register(
        CommandManager.literal("setwakatimeapikey").then(
            CommandManager.argument("key", StringArgumentType.string()).executes(
                SetAPIKey::run)));
  }

  private static int run(CommandContext<ServerCommandSource> context)
      throws CommandSyntaxException {
    ServerPlayerEntity player = context.getSource().getPlayer();
    String key = context.getArgument("key", String.class);
    WakatimeMod.LOGGER.info("Current api key:" + ConfigFile.getApiKey());
    WakatimeMod.LOGGER.info("API Key set for " + player.getServer().getName());
    // context.getSource().sendMessage(Text.of("Test 123"));
    ConfigFile.setApiKey(key);
    context.getSource().sendMessage(Text.of("API key set!"));

    return 0;
  }
}
