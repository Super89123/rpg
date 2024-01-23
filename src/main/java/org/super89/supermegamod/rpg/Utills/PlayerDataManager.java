package org.super89.supermegamod.rpg.Utills;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class PlayerDataManager {
    private Map<UUID, PlayerData> playerDataMap;

    public PlayerDataManager() {
        playerDataMap = new HashMap<>();
        loadData(); // load player data from disk
    }

    public void loadData() {
        try {
            FileInputStream fis = new FileInputStream("player_data.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Map<UUID, PlayerData> loadedData = (Map<UUID, PlayerData>) ois.readObject();
            playerDataMap.putAll(loadedData);
            ois.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveData() {
        try {
            FileOutputStream fos = new FileOutputStream("player_data.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(playerDataMap);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PlayerData getPlayerData(UUID playerId) {
        // If player data doesn't exist, create it and add it to the map
        if (!playerDataMap.containsKey(playerId)) {
            PlayerData playerData = new PlayerData(playerId);
            playerDataMap.put(playerId, playerData);
        }
        return playerDataMap.get(playerId);
    }
}