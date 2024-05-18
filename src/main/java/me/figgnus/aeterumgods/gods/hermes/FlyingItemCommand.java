package me.figgnus.aeterumgods.gods.hermes;

import me.figgnus.aeterumgods.items.CustomItems;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FlyingItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (sender instanceof Player player){
            if (player.hasPermission("aeterumgods.flyingitem.admin")){
                player.getInventory().addItem(CustomItems.createFlyingItem());
            }else {
                player.sendMessage(ChatColor.RED + "You don't have permission to do this");
            }
        }
        return true;
    }
}
