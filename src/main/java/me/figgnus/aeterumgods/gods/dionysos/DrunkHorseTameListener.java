package me.figgnus.aeterumgods.gods.dionysos;

import me.figgnus.aeterumgods.AeterumGods;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;


import java.util.Random;

public class DrunkHorseTameListener implements Listener {
    private final String METADATA_KEY = "DionysusFeed";
    public static final String DRUNK_KEY = "DrunkAura";
    private final AeterumGods plugin;
    Random random = new Random();

    public DrunkHorseTameListener(AeterumGods plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Horse) {
            Horse horse = (Horse) event.getRightClicked();
            Player player = event.getPlayer();
            ItemStack item = player.getInventory().getItemInMainHand();
            if (item == null)return;
            if (item.getItemMeta() == null)return;

            if (item != null && item.hasItemMeta() && item.getItemMeta().hasCustomModelData()
                    && item.getItemMeta().getCustomModelData() == 111) {
                if (!player.hasPermission("aeterumgods.dionysustame.use")) {
                    player.sendMessage(ChatColor.RED + "You don't have permission to do this.");
                    return;
                }

                // Consume one item from the stack
                // item.setAmount(item.getAmount() - 1);

                // Set metadata to indicate the horse has been fed the special item
                plugin.setEntityMetadata(horse, METADATA_KEY, "true");
                // Set metadata to indicate the horse has frost walker ability
                plugin.setEntityMetadata(horse, DRUNK_KEY, "true");
                player.sendMessage("The horse has been fed the special item! You can now tame it to transform it.");
            }
        }
    }
    @EventHandler
    public void onEntityTame(EntityTameEvent event) {
        if (event.getEntity() instanceof Horse) {
            Horse horse = (Horse) event.getEntity();
            Player player = (Player) event.getOwner();

            String metadataValue = plugin.getEntityMetadata(horse, METADATA_KEY);

            // Check if the horse has been fed the special item
            if ("true".equals(metadataValue)) {
                double speed = random.nextDouble(0.3, 0.3375);
                double jump = random.nextDouble(0.9, 1.1);
                int health = random.nextInt(25, 30);

                // Change horse appearance and stats
                horse.setColor(Horse.Color.CHESTNUT);
                horse.setOwner(player);

                // Set horse stats
                horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed); // Fast speed
                horse.getAttribute(Attribute.HORSE_JUMP_STRENGTH).setBaseValue(jump); // High jump
                horse.setMaxHealth(health);
                horse.setHealth(health);

                player.sendMessage("Your horse has transformed!");

                // Remove the metadata so it doesn't affect future taming
                //horse.removeMetadata(METADATA_KEY, plugin);
            }
        }
    }
}
