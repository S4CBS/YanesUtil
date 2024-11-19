/*
 *	MCreator note:
 *
 *	If you lock base mod element files, you can edit this file and the proxy files
 *	and they won't get overwritten. If you change your mod package or modid, you
 *	need to apply these changes to this file MANUALLY.
 *
 *
 *	If you do not lock base mod element files in Workspace settings, this file
 *	will be REGENERATED on each build.
 *
 */
package net.yanes.yanesutil;

import net.yanes.yanesutil.OnJoin.PlayerJoinHandler;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.yanes.yanesutil.init.YanesutilModProcedures;
import net.yanes.yanesutil.init.YanesutilModCommands;

import net.fabricmc.api.ModInitializer;

public class YanesutilMod implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "yanesutil";

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing YanesutilMod");

		YanesutilModProcedures.load();
		YanesutilModCommands.load();
		PlayerJoinHandler.register();

	}
}
