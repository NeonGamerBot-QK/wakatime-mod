package com.saahild.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.saahild.ConfigFile;
import com.saahild.WakatimeMod;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class GetWakatimeInfo {

  public static void register(
      CommandDispatcher<ServerCommandSource> dispatcher,
      CommandRegistryAccess commandRegistryAccess,
      CommandManager.RegistrationEnvironment registrationEnvironment) {
    dispatcher.register(
        CommandManager.literal("getwakatimeinfo").executes(GetWakatimeInfo::run));
  }

  private static int run(CommandContext<ServerCommandSource> context)
      throws CommandSyntaxException {
    String key = ConfigFile.getApiKey();
    String serverURL = ConfigFile.getServerURL();
    WakatimeMod.LOGGER.info("Current api key:" + key);
    WakatimeMod.LOGGER.info("Current server URL:" + serverURL);
    context.getSource().sendMessage(Text.of("Current creds:"));
    context.getSource().sendMessage(Text.of("API Key: " + key));
    context.getSource().sendMessage(Text.of("Server URL: " + serverURL));
    return 0;
  }
}
