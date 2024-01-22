package org.super89.supermegamod.rpg.Utills;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MobUtils {
    public void setAttribute(LivingEntity entity, Attribute attribute, int value) {
    entity.getAttribute(attribute).setBaseValue(value);
}

    public Zombie createZombie(Player player, double x, double y, double z, int health, int damage, String name){
        World world = player.getWorld();
        Location location = new Location(world, x,y,z);
        Zombie zombie = (Zombie) world.spawnEntity(location, EntityType.ZOMBIE);
        zombie.setCanPickupItems(false);
        setAttribute(zombie, Attribute.GENERIC_MAX_HEALTH, health);
        setAttribute(zombie, Attribute.GENERIC_ATTACK_DAMAGE, damage);
        zombie.setCustomName(name);
        zombie.setCanPickupItems(false);
        zombie.getEquipment().setChestplateDropChance(10);
        zombie.getEquipment().setBootsDropChance(10);
        zombie.getEquipment().setLeggingsDropChance(10);
        zombie.getEquipment().setHelmetDropChance(10);

        zombie.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1728000, 0,true));




        return zombie;
    }
}
