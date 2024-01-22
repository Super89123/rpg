package org.super89.supermegamod.rpg;
import com.destroystokyo.paper.Title;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;


public class Event implements Listener{
    public void ZoneSet(String name, Player player, double x1, double y1, double z1, double x2, double y2, double z2) {
        Location min = new Location(player.getWorld(), x1, y1, z1); // bottom-left corner of the region
        Location max = new Location(player.getWorld(), x2, y2, z2); // top-right corner of the region
        for (Entity entity : player.getWorld().getEntities()) {
            if (entity instanceof Player && entity.getLocation().getX() >= min.getX() && entity.getLocation().getY() >= min.getY() && entity.getLocation().getZ() >= min.getZ() && entity.getLocation().getX() <= max.getX() && entity.getLocation().getY() <= max.getY() && entity.getLocation().getZ() <= max.getZ()) {
                Player targetPlayer = (Player) entity;
                Title title = new Title(name);
                targetPlayer.sendTitle(title);
            }
        }
    }



    @EventHandler
    public void LocationEnemySpawn(PlayerMoveEvent event){
        Player player = event.getPlayer();




    }
    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        ZoneSet("Тестовая локация", player, 0,0,0,100,100,100);

    }


}
