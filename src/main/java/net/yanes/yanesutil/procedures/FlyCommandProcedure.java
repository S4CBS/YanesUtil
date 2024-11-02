package net.yanes.yanesutil.procedures;

import net.yanes.yanesutil.YanesutilMod;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import java.util.Map;

public class FlyCommandProcedure {

	public static void execute(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				YanesutilMod.LOGGER.warn("Failed to load dependency entity for procedure FlyCommand!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof Player _player) {
			_player.getAbilities().mayfly = !_player.getAbilities().mayfly;
			_player.getAbilities().flying = !_player.getAbilities().flying;
			_player.onUpdateAbilities();
			// _player.sendSystemMessage(Component.literal("Fly " + (_player.getAbilities().mayfly ? "On" : "Off")));
			_player.displayClientMessage(Component.literal("Fly " + (_player.getAbilities().mayfly ? "On" : "Off")), true);
		}
	}
}