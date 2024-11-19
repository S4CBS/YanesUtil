
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.yanes.yanesutil.init;

import net.yanes.yanesutil.procedures.FixAllCommandProcedure;
import net.yanes.yanesutil.procedures.FixCommandProcedure;
import net.yanes.yanesutil.procedures.GodCommandProcedure;
import net.yanes.yanesutil.procedures.FlyCommandProcedure;

@SuppressWarnings("InstantiationOfUtilityClass")
public class YanesutilModProcedures {
	public static void load() {
		new FlyCommandProcedure();
		new GodCommandProcedure();
		new FixCommandProcedure();
		new FixAllCommandProcedure();
	}
}
