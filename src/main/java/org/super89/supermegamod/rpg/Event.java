package org.super89.supermegamod.rpg;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
public double LocationSet(double x, double y, double z){

    Location location = new Location(x, y, z);
}
public class Event implements Listener{
    @EventHandler
    public void LocationEnemySpawn(PlayerMoveEvent event){
        Player player = event.getPlayer();
        Location

    }


}
