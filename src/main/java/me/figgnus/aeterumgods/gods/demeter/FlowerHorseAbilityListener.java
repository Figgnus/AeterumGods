package me.figgnus.aeterumgods.gods.demeter;

import me.figgnus.aeterumgods.AeterumGods;
import me.figgnus.aeterumgods.gods.poseidon.SeaHorseTameListener;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class FlowerHorseAbilityListener implements Listener {
    private final AeterumGods plugin;

    public FlowerHorseAbilityListener(AeterumGods plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        Player player = event.getPlayer();

        // Check if the player is riding a horse
        if (player.isInsideVehicle() && player.getVehicle() instanceof Horse) {
            Horse horse = (Horse) player.getVehicle();
            String metadataValue = plugin.getEntityMetadata(horse, FlowerHorseTameListener.SEED_KEY);

            // Check if the horse has the Seed  ability
            if ("true".equals(metadataValue)) {
                // Get the block under the horse
                Block centerBlock = horse.getLocation().getBlock().getRelative(0, 0, 0);

                // Iterate through the 3x3 area under the horse
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dz = -1; dz <= 1; dz++) {
                        Block blockBelow = centerBlock.getRelative(dx, -1, dz);
                        Block blockBelowSave = centerBlock.getRelative(dx, 0, dz);

                        // Check if the block below is farmland and is hydrated
                        if (blockBelow.getType() == Material.FARMLAND) {
                            // Check if the player has seeds in their inventory
                            if (player.getInventory().contains(Material.WHEAT_SEEDS)) {
                                plantSeeds(player, blockBelow);
                            }
                        }
                        if (blockBelowSave.getType() == Material.FARMLAND) {
                            if (player.getInventory().contains(Material.WHEAT_SEEDS)){
                                plantSeeds(player, blockBelowSave);
                            }
                        }
                    }
                }
            }
        }
    }

    private void plantSeeds(Player player, Block farmland) {
        // Check the block above is farmland
        Block blockAbove = farmland.getRelative(0, 1, 0);

        // Ensure the block above is air
        if (blockAbove.getType() == Material.AIR){
            // Plant seeds
            blockAbove.setType(Material.WHEAT);

            // Remove seeds from inventory
            ItemStack seeds = new ItemStack(Material.WHEAT_SEEDS, 1);
            player.getInventory().removeItem(seeds);
        }
    }
}
