package org.super89.supermegamod.rpg;
import com.destroystokyo.paper.Title;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.super89.supermegamod.rpg.Utills.PlayerData;
import org.super89.supermegamod.rpg.Utills.PlayerDataManager;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

import static org.bukkit.plugin.java.JavaPlugin.getPlugin;


public class Event implements Listener{
    public Rpg plugin;
    public Event(Rpg plugin){
        this.plugin = plugin;
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
        Configuration configuration = YamlConfiguration.loadConfiguration(new File(Bukkit.getPluginManager().getPlugin("Rpg").getDataFolder() + File.separator + "hashmap.yml" )); // or ;


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
            PlayerDataManager playerDataManager = new PlayerDataManager();
            PlayerData playerData = playerDataManager.getPlayerData(targetPlayer.getUniqueId());

            // Check if the player has already visited this zone
            if (!playerData.getVisitedZones().contains(name)) {
                Title title = new Title(name);
                targetPlayer.sendTitle(title);

                // Add the zone identifier to the set of visited zones
                playerData.addVisitedZone(name);

                // Save player data to disk
                playerDataManager.saveData();
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
    public void OnPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();


    }



}
