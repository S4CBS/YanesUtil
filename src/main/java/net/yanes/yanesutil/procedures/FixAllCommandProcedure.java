package net.yanes.yanesutil.procedures;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

public class FixAllCommandProcedure {
    public static void execute(Map<String, Object> dep) {
        Entity entity = (Entity) dep.get("entity");
        if (entity instanceof Player player){
            boolean repairedAnyItem = false;
            for (int i = 0; i < player.getInventory().getContainerSize(); i++){
                ItemStack itemStack = player.getInventory().getItem(i);
                if (!itemStack.isEmpty() && itemStack.getDamageValue() > 0){
                    itemStack.setDamageValue(0);
                    repairedAnyItem = true;
                }
            }
            if (repairedAnyItem) {
                player.sendSystemMessage(Component.literal("Все поврежденные предметы в инвентаре были починены."));
            } else {
                player.sendSystemMessage(Component.literal("В инвентаре нет поврежденных предметов для починки."));
            }
        }
    }
}
