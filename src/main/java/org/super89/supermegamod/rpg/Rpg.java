package org.super89.supermegamod.rpg;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

public final class Rpg extends JavaPlugin {
    HashMap<Player, Integer> hashmap = new HashMap<Player, Integer>();
    public  void saveHashMap(){
        try {
            YamlConfiguration cfg = new YamlConfiguration();
            for (Player key : hashmap.keySet()){
                cfg.set(String.valueOf(key), hashmap.get(key));
            }
            File f = new File(getDataFolder()+ File.separator+"hashmap.yml");
            if (f.exists()) f.delete();
            cfg.save(f);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public  void loadHashMap(){
        Configuration configuration = YamlConfiguration.loadConfiguration(new File(getDataFolder() + File.separator + "hashmap.yml" )); // or ;


        ConfigurationSection section = configuration.getConfigurationSection("hashmap");
        for (String key : section.getKeys(false)) {
            OfflinePlayer offlinePlayer = configuration.getObject("hashmap." + key, OfflinePlayer.class);
            Player player = Bukkit.getOfflinePlayer(offlinePlayer.getUniqueId()).getPlayer();
            int value = configuration.getInt("hashmap." + key + ".value");
            hashmap.put(player, value);
        }

    }


    @Override
    public void onEnable() {

        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
