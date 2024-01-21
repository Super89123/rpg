package org.super89.supermegamod.rpg.Utills;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;

public class MobUtils {
    public Zombie createZombie(Player player, double x, double y, double z){
        World world = player.getWorld();
        Location location = new Location(world, x,y,z);
        Zombie zombie = (Zombie) world.spawnEntity(location)

        return LivingEntity;
    }
}
