package me.figgnus.aeterumgods.gods.hermes;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpeedBootsListener implements Listener {

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
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 2, false));
        }else{
            player.removePotionEffect(PotionEffectType.SPEED);
        }
    }
}
