package me.figgnus.aeterumgods.gods.poseidon;

import me.figgnus.aeterumgods.AeterumGods;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class SeaHorseAbilityListener implements Listener {
    private final AeterumGods plugin;
    private String FROST_WALKER_KEY = SeaHorseTameListener.FROST_WALKER_KEY;

    public SeaHorseAbilityListener(AeterumGods plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        // Check if the player is riding a horse
        if (player.isInsideVehicle() && player.getVehicle() instanceof Horse) {
            Horse horse = (Horse) player.getVehicle();

            // Check if the horse has the Frost Walker ability
            if (horse.hasMetadata(FROST_WALKER_KEY)) {
                // Get the block under the horse
                Block blockUnder = horse.getLocation().subtract(0, 1, 0).getBlock();

                // Check if the block under the horse is water
                if (blockUnder.getType() == Material.WATER) {
                    // Convert water to ice
                    blockUnder.setType(Material.FROSTED_ICE);

                    // Schedule a task to revert ice back to water after a delay
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (blockUnder.getType() == Material.FROSTED_ICE) {
                                blockUnder.setType(Material.WATER);
                            }
                        }
                    }.runTaskLater(plugin, 100); // 100 ticks = 5 seconds
                }
            }
        }
    }
}
