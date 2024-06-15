package me.figgnus.aeterumgods.gods.demeter;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.UUID;

public class BetterBonemealListener implements Listener {
    private final HashMap<UUID, Long> cooldowns = new HashMap<>();
    private static final int COOLDOWN_TIME = 100; // Cooldown time in milliseconds

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getItem().hasItemMeta()) {
            ItemMeta meta = event.getItem().getItemMeta();
            if (meta.hasCustomModelData() && meta.getCustomModelData() == 113) {
                if (!player.hasPermission("aeterumgods.bonemeal.use")) {
                    player.sendMessage(ChatColor.RED + "You can't use this item.");
                    return;
                }

                long currentTime = System.currentTimeMillis();
                if (cooldowns.containsKey(player.getUniqueId())) {
                    long lastUseTime = cooldowns.get(player.getUniqueId());
                    if (currentTime - lastUseTime < COOLDOWN_TIME) {
                        return;
                    }
                }

                Block block = event.getClickedBlock();
                if (block != null && (block.getType() == Material.CACTUS || block.getType() == Material.SUGAR_CANE)) {
                    Block above = block.getRelative(BlockFace.UP);
                    if (above.getType() == Material.AIR) {
                        // Grow cactus or sugar cane
                        above.setType(block.getType());
                        spawnGrowthParticle(above.getLocation().add(0.5, 0.5, 0.5));
                        if (player.getGameMode() == GameMode.SURVIVAL) {
                            event.getItem().setAmount(event.getItem().getAmount() - 1);
                        }
                        cooldowns.put(player.getUniqueId(), currentTime);
                    }
                }
            }
        }
    }

    private void spawnGrowthParticle(Location location) {
        location.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, location, 30, 0.5, 0.5, 0.5, 0);
    }
}