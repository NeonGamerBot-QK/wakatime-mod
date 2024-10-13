package com.saahild.event;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import com.saahild.MemConfig;
import com.saahild.RequestHandler;
import com.saahild.WakatimeMod;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.net.URISyntaxException;

import org.jetbrains.annotations.Nullable;

// player move event
public class WakatimePlayerMoveEvent implements PlayerBlockBreakEvents.After {

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
    // WakatimeMod.LOGGER.info("Current world level name: " + ) // ODKSJHIFHUEFIUS;
    // TODO: add wakatime heartbeat here!
    try {
      RequestHandler.sendHeartbeat(MemConfig.getApiKey(), "Minecraft", "https://wakatime.com/api/v1",
          serverName);
    } catch (URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
