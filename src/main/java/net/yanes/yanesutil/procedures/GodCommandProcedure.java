package net.yanes.yanesutil.procedures;

import net.yanes.yanesutil.YanesutilMod;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import java.util.Map;

public class GodCommandProcedure {

	public static void execute(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				YanesutilMod.LOGGER.warn("Failed to load dependency entity for procedure GodCommand!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof Player _player) {
			_player.getAbilities().invulnerable = !_player.getAbilities().invulnerable;
			_player.onUpdateAbilities();
			_player.displayClientMessage(Component.literal("God " + (_player.getAbilities().invulnerable ? "On" : "Off")), true);
		}
	}
}
