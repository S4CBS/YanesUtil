package net.yanes.yanesutil.hud;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeHud {
    private static boolean isHudEnabled = false;
    public static void toggleHud() {
        isHudEnabled = !isHudEnabled;
    }
    public static boolean isIsHudEnabled() {
        return isHudEnabled;
    }

    public static void renderHud(PoseStack poseStack, float v) {
        if (!isHudEnabled) {
            return; // Если HUD выключен, ничего не рендерим
        }

        Minecraft client = Minecraft.getInstance();
        if (client.player == null || client.level == null) {
            return;
        }

        // Получение реального времени
        String realTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        // Получение игрового времени
        long worldTime = client.level.getDayTime();
        int hours = (int) ((worldTime / 1000 + 6) % 24);
        int minutes = (int) ((worldTime % 1000) * 60 / 1000);

        // Подготовка текста для отображения
        String gameTime = String.format("%02d:%02d", hours, minutes);
        String displayText = "Real Time: " + realTime + " | Game Time: " + gameTime;

        // Рендер текста
        client.font.draw(poseStack, displayText, 10, 10, 0x191970);
    }
}
