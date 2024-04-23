package me.figgnus.aeterumgods.hermes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class HermesListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        // Check if the player is holding the custom item
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        if (!(player.hasPermission("aeterumgods.hermes"))){
            event.setCancelled(true);
        }
        if (player.hasPermission("aeterumgods.hermes"))
        if (item != null && item.getType() == Material.STONE_SWORD && item.getItemMeta().hasCustomModelData() && item.getItemMeta().getCustomModelData() == 102) {
            if (event.getPlayer().isGliding()) {
                // Propel the player
                Vector direction = event.getPlayer().getLocation().getDirection();
                event.getPlayer().setVelocity(direction.multiply(1.5)); // Adjust the multiplier for speed

                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1f,1f);

                // Reduce the durability of the item
                item.setDurability((short) (item.getDurability() + 1));

                // Cancel the event to prevent default action
                event.setCancelled(true);
            }
        }
    }
}
