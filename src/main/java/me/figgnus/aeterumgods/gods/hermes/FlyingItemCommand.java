package me.figgnus.aeterumgods.gods.hermes;

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
                player.getInventory().addItem(createCustomItem());
            }else {
                player.sendMessage(ChatColor.RED + "You don't have permission to do this");
            }
        }
        return true;
    }

    private ItemStack createCustomItem() {
        ItemStack customSword = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = customSword.getItemMeta();

        // Set custom model data (ensure the number is unique)
        meta.setCustomModelData(102);
        meta.setDisplayName(ChatColor.GREEN + "Fly! Forrest Fly!");
        customSword.setItemMeta(meta);

        // Optional: Add the item to some inventory or give it to players
        return customSword;
    }
}
