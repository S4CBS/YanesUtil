package net.yanes.yanesutil.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.yanes.yanesutil.hud.TimeHud;

public class TimeHudCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext) {
        dispatcher.register(Commands.literal("timehudtoggle")
                .executes(arguments ->{
                    TimeHud.toggleHud();
                    boolean state = TimeHud.isIsHudEnabled();
                    arguments.getSource().sendSuccess(Component.literal("Time HUD " + (state ? "Enabled" : "Disabled")), true);
                    return 1;
                }));
    }
}
