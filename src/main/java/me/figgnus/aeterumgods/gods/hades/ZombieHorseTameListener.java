package me.figgnus.aeterumgods.gods.hades;

import me.figgnus.aeterumgods.AeterumGods;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class ZombieHorseTameListener implements Listener {
    private final String METADATA_KEY = "HadesFeed";
    public static final String LAVA_WALKER = "LavaWalker";
    private final AeterumGods plugin;
    Random random = new Random();

    public ZombieHorseTameListener(AeterumGods plugin) {
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
                    && item.getItemMeta().getCustomModelData() == 106) {
                if (!player.hasPermission("aeterumgods.hadestame.use")) {
                    player.sendMessage(ChatColor.RED + "You don't have permission to do this.");
                    return;
                }
                // Consume one item from the stack
                // item.setAmount(item.getAmount() - 1);

                // Set metadata to indicate the horse has been fed the special item
                plugin.setEntityMetadata(horse, METADATA_KEY, "true");



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
                // Remove the horse
                Location location = horse.getLocation();
                horse.remove();

                // Spawn zombie horse at the same location
                ZombieHorse zombieHorse = (ZombieHorse) horse.getWorld().spawnEntity(location, EntityType.ZOMBIE_HORSE);

                // Apply setting to the zombie horse
                double speed = random.nextDouble(0.3, 0.3375);
                double jump = random.nextDouble(0.9, 1.1);
                int health = random.nextInt(25, 30);

                // Change horse appearance and stats
                zombieHorse.setTamed(true);
                zombieHorse.setOwner(player);

                // Set horse stats
                zombieHorse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed); // Fast speed
                zombieHorse.getAttribute(Attribute.HORSE_JUMP_STRENGTH).setBaseValue(jump); // High jump
                zombieHorse.setMaxHealth(health);
                zombieHorse.setHealth(health);

                // Set metadata to indicate the horse has frost walker ability
                plugin.setEntityMetadata(zombieHorse, LAVA_WALKER, "true");

                player.sendMessage("Your horse has transformed!");

                // Remove the metadata so it doesn't affect future taming
                //horse.removeMetadata(METADATA_KEY, plugin);
            }
        }
    }
}
