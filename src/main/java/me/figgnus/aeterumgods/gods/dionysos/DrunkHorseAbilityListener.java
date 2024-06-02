package me.figgnus.aeterumgods.gods.dionysos;

import com.dre.brewery.BPlayer;
import me.figgnus.aeterumgods.AeterumGods;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DrunkHorseAbilityListener implements Listener {
    private final AeterumGods plugin;
    private final Map<UUID, BukkitRunnable> horseTasks = new HashMap<>();

    public DrunkHorseAbilityListener(AeterumGods plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        // Check if the player is riding a horse
        if (player.isInsideVehicle() && player.getVehicle() instanceof Horse) {
            Horse horse = (Horse) player.getVehicle();
            String metadataValue = plugin.getEntityMetadata(horse, DrunkHorseTameListener.DRUNK_KEY);
            if ("true".equals(metadataValue)){
                startAuraTask(player, horse);
            }
        }
    }

    private void startAuraTask(Player player, Horse horse) {
        UUID horseUUID = horse.getUniqueId();

        // Cancel any existing task for this horse
        if (horseTasks.containsKey(horseUUID)) {
            horseTasks.get(horseUUID).cancel();
        }

        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                if (horse.isDead() || !player.isOnline() || !player.isInsideVehicle() || !player.getVehicle().equals(horse)) {
                    this.cancel();
                    horseTasks.remove(horseUUID);
                    return;
                }

                // Get the player's drunkenness level
                BPlayer bPlayer = BPlayer.get(player);
                int drunkenness = bPlayer.getDrunkeness();

                if (drunkenness > 0) {
                    // Calculate damage based on drunkenness level
                    double damage = drunkenness * 0.1; // Example: 0.1 damage per drunkenness level

                    // Apply damage to nearby entities
                    for (Entity entity : horse.getNearbyEntities(2, 2, 2)) {
                        if (entity instanceof LivingEntity && entity != player) {
                            ((LivingEntity) entity).damage(damage);

                            // Spawn particle effects at the location of the damaged entity
                            Location loc = entity.getLocation();
                            entity.getWorld().spawnParticle(Particle.SPELL_WITCH, loc, 10, 0.5, 0.5, 0.5, 0.01);
                        }
                    }
                }
            }
        };

        task.runTaskTimer(plugin, 0, 20); // Run task every second (20 ticks)
        horseTasks.put(horseUUID, task);
    }
}
