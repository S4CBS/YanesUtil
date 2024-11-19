package net.yanes.yanesutil.OnJoin;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.yanes.yanesutil.config.Config;

import java.util.UUID;

public class PlayerJoinHandler {
    public static void register() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayer player = handler.getPlayer();
            UUID playerUUID = player.getUUID();
            ServerLevel world = player.getLevel();
            String worldName = world.getServer().getWorldData().getLevelName();

            boolean canFly = Config.getState("Fly", worldName, playerUUID);
            boolean canGod = Config.getState("God", worldName, playerUUID);

            player.getAbilities().mayfly = canFly;
            player.getAbilities().invulnerable = canGod;
            player.onUpdateAbilities();
        });
    }
}
