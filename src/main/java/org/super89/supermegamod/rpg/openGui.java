package org.super89.supermegamod.rpg;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class openGui implements CommandExecutor {
    public Rpg plugin;
    public openGui(Rpg plugin){
        this.plugin = plugin;
    }
    Event event = new Event(JavaPlugin.getPlugin(Rpg.class));
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(command.getName().equalsIgnoreCase("lvl")){
            Player player = (Player) commandSender;
            event.ProkachkaGUI(player);
            return true;

        }

        return false;
    }
}
