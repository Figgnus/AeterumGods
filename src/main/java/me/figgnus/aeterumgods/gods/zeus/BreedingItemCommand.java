package me.figgnus.aeterumgods.gods.zeus;

import me.figgnus.aeterumgods.items.CustomItems;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BreedingItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player player){
            if (player.hasPermission("aeterumgods.breeding.admin")){
                player.getInventory().addItem(CustomItems.createBreedingItem());
            }else {
                player.sendMessage(ChatColor.RED + "You don't have permission to do this");
            }
        }
        return true;
    }
}
