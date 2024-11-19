package net.yanes.yanesutil.procedures;

import net.minecraft.server.level.ServerLevel;
import net.yanes.yanesutil.YanesutilMod;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.yanes.yanesutil.config.Config;

import java.util.Map;
import java.util.UUID;

public class GodCommandProcedure {

	public static void execute(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null || dependencies.get("world") == null) {
			if (!dependencies.containsKey("entity"))
				YanesutilMod.LOGGER.warn("Failed to load dependency entity for procedure GodCommand!");
			return;
		}
		ServerLevel world = (ServerLevel) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof Player _player) {
			String worldName = world.getServer().getWorldData().getLevelName();
			UUID playerUUID = _player.getUUID();

			// Обновляем состояние режима Бога
			boolean newGodState = Config.getState("God", worldName, playerUUID);
			Config.setState("God", worldName, playerUUID, !newGodState);

			_player.getAbilities().invulnerable = !_player.getAbilities().invulnerable;
			_player.onUpdateAbilities();
			_player.displayClientMessage(Component.literal("God " + (_player.getAbilities().invulnerable ? "On" : "Off")), true);
		}
	}
}
