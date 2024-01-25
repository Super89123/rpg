package org.super89.supermegamod.rpg;
import com.destroystokyo.paper.Title;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;

import org.super89.supermegamod.rpg.Utills.ItemUtils;


import java.io.File;
import java.io.IOException;



public class Event implements Listener{
    public Rpg plugin;
    public Event(Rpg plugin){
        this.plugin = plugin;
    }
    public void ProkachkaGUI(Player player){
        String playerUUID = player.getUniqueId().toString();
        File playerDataFile = new File(plugin.getDataFolder(), "playerdata.yml");
        FileConfiguration playerDataConfig = YamlConfiguration.loadConfiguration(playerDataFile);
        int a = playerDataConfig.getInt(playerUUID + "." + "op");
        Inventory inv = Bukkit.createInventory(null, 54,"§4Дерево прокачки");
        inv.setItem(10, ItemUtils.create(Material.APPLE, 1, (byte) 1, "§6Прокачать хп", "§fТекущие количество очков", "прокачки: "+ a, null,null));
        inv.setItem(20, ItemUtils.create(Material.ARROW, 1, (byte) 1, "§6Прокачать урон", "§fТекущие количество очков", "прокачки: " + a, null,null));
        inv.setItem(30, ItemUtils.create(Material.SPONGE, 1, (byte) 1, "§6Прокачать скорость", "§fТекущие количество очков", "прокачки: " + a, null,null));
        player.openInventory(inv);
    }
    public void Prokachka(Player player){
        String playerUUID = player.getUniqueId().toString();
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

    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        String playerUUID = player.getUniqueId().toString();
        File playerDataFile = new File(plugin.getDataFolder(), "playerdata.yml");
        FileConfiguration playerDataConfig = YamlConfiguration.loadConfiguration(playerDataFile);

        if (event.getView().getTitle().equalsIgnoreCase("§4Дерево прокачки") && event.getCurrentItem() != null) {
            event.setCancelled(true);
            if (event.getCurrentItem().getType() == Material.APPLE) {
                AttributeInstance health = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                if (playerDataConfig.getInt(playerUUID + "." + "op") > 0) {

                    playerDataConfig.set(playerUUID + "." + "op", playerDataConfig.getInt(playerUUID + "." + "op") - 1);
                    health.setBaseValue(health.getValue() + 1);
                    player.sendMessage("§6Вы успешно повысили свой хп!");
                    try {
                        playerDataConfig.save(playerDataFile);
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                }
                else{
                    player.sendMessage("§cУ вас недостаточно Очков Прокачки");}
                if (event.getCurrentItem().getType() == Material.ARROW) {
                    AttributeInstance damage = player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
                    if (playerDataConfig.getInt(playerUUID + "." + "op") > 0) {

                        playerDataConfig.set(playerUUID + "." + "op", playerDataConfig.getInt(playerUUID + "." + "op") - 1);
                        damage.setBaseValue(damage.getValue() + 0.5);
                        player.sendMessage("§6Вы успешно повысили свой урон!");
                        try {
                            playerDataConfig.save(playerDataFile);
                        } catch (IOException e) {
                            e.printStackTrace();

                        }
                    }
                    else{
                        player.sendMessage("§cУ вас недостаточно Очков Прокачки");}
                }
                if(event.getCurrentItem().getType() == Material.SPONGE){
                    AttributeInstance speed = player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
                    if(playerDataConfig.getInt(playerUUID + "."+ "op") > 0){

                        playerDataConfig.set(playerUUID + "." + "op",playerDataConfig.getInt(playerUUID + "." + "op")-1);
                        speed.setBaseValue(speed.getValue()+0.01);
                        player.sendMessage("§6Вы успешно повысили свой хп!");
                        try {
                            playerDataConfig.save(playerDataFile);
                        } catch (IOException e) {
                            e.printStackTrace();

                        }
                    }
                    else{
                    player.sendMessage("§cУ вас недостаточно Очков Прокачки");}
                }
            }
        }

    }

}
