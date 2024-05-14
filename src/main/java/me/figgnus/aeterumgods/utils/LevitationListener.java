package me.figgnus.aeterumgods.utils;

import me.figgnus.aeterumgods.AeterumGods;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class LevitationListener implements Listener {
    private final AeterumGods plugin;

    public LevitationListener(AeterumGods plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        // Check if the player is riding a horse and has the levitation effect
        if (player.isInsideVehicle() && player.getVehicle() instanceof Horse && player.hasPotionEffect(PotionEffectType.LEVITATION)) {
            if (!player.getAllowFlight()) {
                player.setAllowFlight(true);
            }

            // Schedule a task to check and remove flying permission after the levitation effect ends
            new BukkitRunnable() {
                @Override
                public void run() {
                    // Check if the player still has the levitation effect
                    if (!player.hasPotionEffect(PotionEffectType.LEVITATION)) {
                        player.setAllowFlight(false);
                        player.setFlying(false);
                    }
                }
            }.runTaskLater(plugin, getRemainingLevitationDuration(player));
        }
    }

    private long getRemainingLevitationDuration(Player player) {
        for (PotionEffect effect : player.getActivePotionEffects()) {
            if (effect.getType().equals(PotionEffectType.LEVITATION)) {
                return effect.getDuration();
            }
        }
        return 0;
    }
}
