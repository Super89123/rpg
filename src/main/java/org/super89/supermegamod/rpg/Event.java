package org.super89.supermegamod.rpg;
import com.destroystokyo.paper.Title;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import static org.bukkit.plugin.java.JavaPlugin.getPlugin;


public class Event implements Listener{
    public Rpg plugin;
    public Event(Rpg plugin){
        this.plugin = plugin;
    }
    public void Prokachka(Player player){
        Player targetPlayer = player;
        String playerUUID = targetPlayer.getUniqueId().toString();
        File playerDataFile = new File(plugin.getDataFolder(), "playerdata.yml");
        FileConfiguration playerDataConfig = YamlConfiguration.loadConfiguration(playerDataFile);
        int a = playerDataConfig.getInt(playerUUID + "." + "op");



            playerDataConfig.set(playerUUID + "." + "op", a+1);
            try {
                playerDataConfig.save(playerDataFile);
            } catch (IOException e) {
                e.printStackTrace();
            }


    }
/*   public  void saveHashMap(){
        try {
            YamlConfiguration cfg = new YamlConfiguration();
            for (Player key : hashmap.keySet()){
                cfg.set(String.valueOf(key), hashmap.get(key));
            }
            File f = new File(plugin.getDataFolder()+ File.separator+"hashmap.yml");
            if (f.exists()) f.delete();
            cfg.save(f);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public HashMap<Player, Integer> loadHashMap(){
        Configuration configuration = YamlConfiguration.loadConfiguration(new File(Bukkit.getPluginManager().getPlugin("Rpg").getDataFolder() + File.separator + "hashmap.yml" ));


        ConfigurationSection section = configuration.getConfigurationSection("hashmap");
        for (String key : section.getKeys(false)) {
            OfflinePlayer offlinePlayer = configuration.getObject("hashmap." + key, OfflinePlayer.class);
            Player player = Bukkit.getOfflinePlayer(offlinePlayer.getUniqueId()).getPlayer();
            int value = configuration.getInt("hashmap." + key + ".value");
            hashmap.put(player, value);
            return hashmap;
        }

        return null;
    }




*/
public void ZoneSet(String name, Player player, double x1, double y1, double z1, double x2, double y2, double z2) {
    Location min = new Location(player.getWorld(), x1, y1, z1); // bottom-left corner of the region
    Location max = new Location(player.getWorld(), x2, y2, z2); // top-right corner of the region
    for (Entity entity : player.getWorld().getEntities()) {
        if (entity instanceof Player && entity.getLocation().getX() >= min.getX() && entity.getLocation().getY() >= min.getY() && entity.getLocation().getZ() >= min.getZ() && entity.getLocation().getX() <= max.getX() && entity.getLocation().getY() <= max.getY() && entity.getLocation().getZ() <= max.getZ()) {
            Player targetPlayer = (Player) entity;
            String playerUUID = targetPlayer.getUniqueId().toString();
            File playerDataFile = new File(plugin.getDataFolder(), "playerdata.yml");
            FileConfiguration playerDataConfig = YamlConfiguration.loadConfiguration(playerDataFile);

            if (!playerDataConfig.contains(playerUUID + "." + name)) {
                Title title = new Title(name);
                targetPlayer.sendTitle(title);

                playerDataConfig.set(playerUUID + "." + name, true);
                try {
                    playerDataConfig.save(playerDataFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

    @EventHandler
    public void LocationEnemySpawn(PlayerMoveEvent event){
        Player player = event.getPlayer();
        ZoneSet("Тестовая локация", player, 0,0,0,100,100,100);





    }
    @EventHandler
    public void OnPlayerJoin(PlayerExpChangeEvent event){
        Player player = event.getPlayer();
        Prokachka(player);


    }



}
