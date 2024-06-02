package me.figgnus.aeterumgods.gods.hermes;

import me.figgnus.aeterumgods.AeterumGods;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class SpeedHorseAbilityListener implements Listener {
    private final AeterumGods plugin;

    public SpeedHorseAbilityListener(AeterumGods plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item.getItemMeta() == null)return;

        if (item.getType() == Material.POTION && player.isInsideVehicle() && player.getVehicle() instanceof Horse && item.getItemMeta().getCustomModelData() == 112) {
            if (!player.hasPermission("aeterumgods.hermestame.use")){
                player.sendMessage(ChatColor.RED + "You don't have permission to do this.");
                return;
            }
            Horse horse = (Horse) player.getVehicle();
            String metadataValue = plugin.getEntityMetadata(horse, SpeedHorseTameListener.SPEED_KEY);
            if ("true".equals(metadataValue)){
                // Apply levitation effect to the horse
                horse.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 3)); // 100 ticks = 5 seconds
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 3)); // 100 ticks = 5 seconds
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (!horse.hasPotionEffect(PotionEffectType.SPEED)) {
                            this.cancel(); // Cancel the task if the horse no longer has the speed effect
                            return;
                        }
                        // Spawn particles at the horse's location
                        horse.getWorld().spawnParticle(Particle.CLOUD, horse.getLocation().add(0, 1, 0), 10, 0.5, 0.5, 0.5, 0.02);
                    }
                }.runTaskTimer(plugin, 0, 5); // Run task every 5 ticks (0.25 seconds)
            }
        }
    }
}
