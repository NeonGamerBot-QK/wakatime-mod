package com.saahild.event;

import com.saahild.WakatimeMod;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

// player move event
public class WakatimePlayerMoveEvent implements PlayerBlockBreakEvents.After {

  @Override
  public void afterBlockBreak(
    World world,
    PlayerEntity player,
    BlockPos pos,
    BlockState state,
    @Nullable BlockEntity blockEntity
  ) {
    WakatimeMod.LOGGER.info(
      "Player " +
      player.getName() +
      " broke a block at " +
      pos.getX() +
      ", " +
      pos.getY() +
      ", " +
      pos.getZ()
    );
    // TODO: add wakatime heartbeat here!

  }
}
