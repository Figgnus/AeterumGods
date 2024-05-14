package me.figgnus.aeterumgods.gods.hermes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class FlyingItemListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        // Check if the player is holding the custom item
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        if (item == null)return;
        if (item.getItemMeta() == null)return;

        if (item != null && item.getType() == Material.STONE_SWORD &&
                item.getItemMeta().hasCustomModelData() &&
                item.getItemMeta().getCustomModelData() == 102) {
            if (!(player.hasPermission("aeterumgods.flyingitem.use"))){
                player.sendMessage(ChatColor.RED + "You can't use this item.");
                return;
            }
            ItemMeta meta = item.getItemMeta();
            Damageable damageable = (Damageable) meta;
            int currentDamage = damageable.getDamage();
            if (currentDamage == item.getType().getMaxDurability()){
                return;
            }

            if (event.getPlayer().isGliding()) {
                // Propel the player
                Vector direction = event.getPlayer().getLocation().getDirection();
                event.getPlayer().setVelocity(direction.multiply(1.5)); // Adjust the multiplier for speed

                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1f,1f);

                // Reduce the durability of the item
                adjustItemDurability(item, 1);


                // Cancel the event to prevent default action
                event.setCancelled(true);
            }
        }
    }

    private void adjustItemDurability(ItemStack item, int i) {
        // Check if the item has meta data
        if (item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();

            // Check if the meta data is an instance of Damageable
            if (meta instanceof Damageable damageable) {

                // Get current damage
                int currentDamage = damageable.getDamage();

                // Modify the damage (increase for wear, decrease for repair)
                damageable.setDamage(currentDamage + i);

                // Check for over-damage (optional, if you want to prevent breaking)
                if (damageable.getDamage() > item.getType().getMaxDurability()) {
                    damageable.setDamage(item.getType().getMaxDurability());
                }

                // Set the modified meta back to the item
                item.setItemMeta((ItemMeta) damageable);
            }
        }
    }
}
