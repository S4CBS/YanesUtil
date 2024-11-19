package net.yanes.yanesutil.config;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Config {
    private static final String CONFIG_PATH = "config/YanesUtil.json";

    // Основная структура данных
    private static Map<String, Map<String, Map<UUID, Boolean>>> configData = new HashMap<>();

    static {
        loadConfig();
    }

    // Загружаем конфигурацию из файла
    public static void loadConfig() {
        try (FileReader reader = new FileReader(CONFIG_PATH)) {
            Type type = new TypeToken<Map<String, Map<String, Map<UUID, Boolean>>>>() {}.getType();
            configData = new Gson().fromJson(reader, type);
            if (configData == null) {
                configData = new HashMap<>();
            }
        } catch (Exception e) {
            configData = new HashMap<>();
        }
    }

    // Сохраняем конфигурацию в файл
    public static void saveConfig() {
        try (FileWriter writer = new FileWriter(CONFIG_PATH)) {
            new Gson().toJson(configData, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Получаем состояние для заданной команды (например, "Fly", "God")
    public static boolean getState(String command, String worldName, UUID playerUUID) {
        if (configData.containsKey(command)) {
            Map<String, Map<UUID, Boolean>> worldData = configData.get(command);
            if (worldData.containsKey(worldName)) {
                Map<UUID, Boolean> playerData = worldData.get(worldName);
                return playerData.getOrDefault(playerUUID, false);
            }
        }
        return false; // По умолчанию отключено
    }

    // Устанавливаем состояние для заданной команды
    public static void setState(String command, String worldName, UUID playerUUID, boolean state) {
        configData.computeIfAbsent(command, k -> new HashMap<>())
                .computeIfAbsent(worldName, k -> new HashMap<>())
                .put(playerUUID, state);
        saveConfig();
    }

    // Удаляем состояние для заданной команды
    public static void removeState(String command, String worldName, UUID playerUUID) {
        if (configData.containsKey(command)) {
            Map<String, Map<UUID, Boolean>> worldData = configData.get(command);
            if (worldData.containsKey(worldName)) {
                Map<UUID, Boolean> playerData = worldData.get(worldName);
                playerData.remove(playerUUID);
                if (playerData.isEmpty()) {
                    worldData.remove(worldName);
                }
                if (worldData.isEmpty()) {
                    configData.remove(command);
                }
                saveConfig();
            }
        }
    }
}