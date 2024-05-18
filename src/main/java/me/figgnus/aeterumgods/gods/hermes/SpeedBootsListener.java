package me.figgnus.aeterumgods.gods.hermes;

import me.figgnus.aeterumgods.items.CustomItems;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpeedBootsListener implements Listener, CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
        }
        Player player = (Player) sender;
        if (!player.hasPermission("aeterumgods.speedboots.admin")){
            player.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
        }
        player.getInventory().addItem(CustomItems.createCustomBoots());
        return true;
    }
    @EventHandler
    private void onPlayerMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        ItemStack boots = player.getInventory().getBoots();
        if (boots == null){
            return;
        }
        if (boots.getItemMeta() == null){
            return;
        }


        if (boots != null && boots.getItemMeta().getCustomModelData() == 104){
            if (!(player.hasPermission("aeterumgods.speedboots.use"))){
                player.sendMessage(ChatColor.RED + "You can't use this item.");
                return;
            }
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 0, false));
        }else{
            player.removePotionEffect(PotionEffectType.SPEED);
        }
    }
}
