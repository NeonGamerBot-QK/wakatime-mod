package com.saahild.event;

import com.saahild.ConfigFile;
import com.saahild.RequestHandler;
import com.saahild.WakatimeMod;
import java.net.URISyntaxException;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

// player move event
public class WakatimePlayerBlockBreak implements PlayerBlockBreakEvents.After {

  @Override
  public void afterBlockBreak(
      World world,
      PlayerEntity player,
      BlockPos pos,
      BlockState state,
      @Nullable BlockEntity blockEntity) {
    WakatimeMod.LOGGER.info(
        "Player " +
            player.getName() +
            " broke a block at " +
            pos.getX() +
            ", " +
            pos.getY() +
            ", " +
            pos.getZ());
    String serverName = world.getServer().getName();
    if (!world.getServer().isRemote()) {
      // get world name of single player world
      serverName = world.getServer().getServerMotd().split(" - ")[1];
    }
    WakatimeMod.LOGGER.info("Current server name: " + serverName);
    WakatimeMod.LOGGER.info("Current api key " + ConfigFile.getApiKey());
    if (!ConfigFile.canSend())
      return;
    try {
      RequestHandler.sendHeartbeat(
          ConfigFile.getApiKey(),
          "Minecraft",
          ConfigFile.getServerURL(),
          serverName);
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
  }
}
