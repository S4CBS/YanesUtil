package net.yanes.yanesutil.procedures;

import net.minecraft.server.level.ServerLevel;
import net.yanes.yanesutil.YanesutilMod;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.yanes.yanesutil.config.Config;

import java.util.Map;
import java.util.UUID;

public class FlyCommandProcedure {

	public static void execute(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null || dependencies.get("world") == null) {
			if (!dependencies.containsKey("entity"))
				YanesutilMod.LOGGER.warn("Failed to load dependency entity for procedure FlyCommand!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ServerLevel world = (ServerLevel) dependencies.get("world");
		if (entity instanceof Player _player) {
			UUID playerUUID = _player.getUUID();
			// Название мира
			String worldName = world.getServer().getWorldData().getLevelName();

			boolean newFlyState = Config.getState("Fly", worldName, playerUUID);
			Config.setState("Fly", worldName, playerUUID, !newFlyState);

			_player.getAbilities().mayfly = !_player.getAbilities().mayfly;
			_player.getAbilities().flying = !_player.getAbilities().flying;
			_player.onUpdateAbilities();

			// _player.sendSystemMessage(Component.literal("Fly " + (_player.getAbilities().mayfly ? "On" : "Off")));
			_player.displayClientMessage(Component.literal("Fly " + (_player.getAbilities().mayfly ? "On" : "Off")), true);
		}
	}
}
