package net.yanes.yanesutil.procedures;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

public class FixCommandProcedure {
    public static void execute(Map<String, Object> dep) {
        if (dep.get("entity") == null) {
            return;
        }
        Entity entity = (Entity) dep.get("entity");
        if (entity instanceof Player player) {
            ItemStack mainHandItem = player.getMainHandItem();
            if (!mainHandItem.isEmpty()) {
                int damage = mainHandItem.getDamageValue();
                int maxDamage = mainHandItem.getMaxDamage();
                if (damage != 0) {
                    mainHandItem.setDamageValue(0);
                    player.sendSystemMessage(Component.literal("Предмет: " + mainHandItem.getDisplayName().getString() + " был починен."));
                } else if (damage == 0 && maxDamage == 0) {
                    player.sendSystemMessage(Component.literal("Этот предмет нельзя починить."));
                } else {
                    player.sendSystemMessage(Component.literal("Предмет в руке не сломан."));
                }
            } else {
                player.sendSystemMessage(Component.literal("В руке нет предмета."));
            }
        }
    }
}
