package me.figgnus.aeterumgods.gods.zeus;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BreedingItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player player){
            if (player.hasPermission("aeterumgods.breeding.admin")){
                player.getInventory().addItem(createBreedingItem());
            }else {
                player.sendMessage(ChatColor.RED + "You don't have permission to do this");
            }

        }
        return true;
    }
    private ItemStack createBreedingItem(){
        ItemStack breedingItem = new ItemStack(Material.SLIME_BALL);
        ItemMeta breedingItemMeta = breedingItem.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemLore.add("This Egg lets you breed animals");

        breedingItemMeta.setCustomModelData(103);
        breedingItemMeta.setDisplayName(ChatColor.GOLD + "Magic Egg");
        breedingItemMeta.setLore(itemLore);
        breedingItem.setItemMeta(breedingItemMeta);

        return breedingItem;
    }
}
