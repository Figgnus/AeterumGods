package me.figgnus.aeterumgods.gods.hades;

import me.figgnus.aeterumgods.AeterumGods;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.ZombieHorse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class ZombieHorseAbilityListener implements Listener {

    private final AeterumGods plugin;

    public ZombieHorseAbilityListener(AeterumGods plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        // Check if the player is riding a horse
        if (player.isInsideVehicle() && player.getVehicle() instanceof ZombieHorse) {
            ZombieHorse zombieHorse = (ZombieHorse) player.getVehicle();
            String metadataValue = plugin.getEntityMetadata(zombieHorse, ZombieHorseTameListener.LAVA_WALKER);

            // Check if the horse has the Frost Walker ability
            if ("true".equals(metadataValue)) {
                // Get the block under the horse
                Block blockUnder = zombieHorse.getLocation().subtract(0, 1, 0).getBlock();
                zombieHorse.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1000, 1));

                // Check if the block under the horse is lava
                if (blockUnder.getType() == Material.LAVA) {
                    // Convert lava to ice
                    blockUnder.setType(Material.BASALT);

                    // Schedule a task to revert ice back to water after a delay
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (blockUnder.getType() == Material.BASALT) {
                                blockUnder.setType(Material.LAVA);
                            }
                        }
                    }.runTaskLater(plugin, 100); // 100 ticks = 5 seconds
                }
            }
        }
    }
}
