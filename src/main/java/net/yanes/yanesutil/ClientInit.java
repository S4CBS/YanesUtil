package net.yanes.yanesutil;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.yanes.yanesutil.command.TimeHudCommand;
import net.yanes.yanesutil.hud.TimeHud;

@Environment(EnvType.CLIENT)
public class ClientInit implements ClientModInitializer {
	PoseStack poseStack;
	float v;
	@Override
	public void onInitializeClient() {
		HudRenderCallback.EVENT.register(TimeHud::renderHud);
		CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> {
			TimeHudCommand.register(dispatcher, registryAccess);
		}));
	}
}
