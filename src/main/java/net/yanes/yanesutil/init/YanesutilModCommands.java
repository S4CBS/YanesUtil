
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.yanes.yanesutil.init;

import net.yanes.yanesutil.command.FixCommand;
import net.yanes.yanesutil.command.GodCommand;
import net.yanes.yanesutil.command.FlyCommand;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class YanesutilModCommands {
	public static void load() {
		CommandRegistrationCallback.EVENT.register((dispatcher, commandBuildContext, dedicated) -> {
			FlyCommand.register(dispatcher, commandBuildContext);
			GodCommand.register(dispatcher, commandBuildContext);
			FixCommand.register(dispatcher, commandBuildContext);
		});
	}
}
