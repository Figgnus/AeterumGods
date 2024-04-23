package me.figgnus.aeterumgods.poseidon;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TridentCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (sender instanceof Player player){
            if (player.hasPermission("aeterumgods.poseidon")){
                player.getInventory().addItem(createTrident());
            }else {
                player.sendMessage(ChatColor.RED + "You don't have permission to do this");
            }

        }
        return true;
    }
    private ItemStack createTrident(){
        ItemStack trident = new ItemStack(Material.TRIDENT);
        ItemMeta meta = trident.getItemMeta();

        meta.setDisplayName(ChatColor.RED + "Poseidonův Trident");
        meta.setCustomModelData(101);
        meta.addEnchant(Enchantment.IMPALING, 7, true);
        meta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
        meta.addEnchant(Enchantment.MENDING, 1, true);
        meta.addEnchant(Enchantment.RIPTIDE, 3, true);
        meta.addEnchant(Enchantment.DURABILITY, 3, true);
        meta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 3, true);

        trident.setItemMeta(meta);

        return trident;
    }
}
