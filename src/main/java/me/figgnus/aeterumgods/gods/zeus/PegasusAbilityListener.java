package me.figgnus.aeterumgods.gods.zeus;

import me.figgnus.aeterumgods.AeterumGods;
import me.figgnus.aeterumgods.items.CustomItems;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class PegasusAbilityListener implements Listener {
    private final AeterumGods plugin;

    public PegasusAbilityListener(AeterumGods plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item == null)return;
        if (item.getItemMeta() == null)return;
        if (!item.hasItemMeta())return;

        if (item.getType() == Material.POTION && player.isInsideVehicle() && player.getVehicle() instanceof Horse && item.getItemMeta().getCustomModelData() == 107) {
            if (!player.hasPermission("aeterumgods.zeustame.use")){
                player.sendMessage(ChatColor.RED + "You don't have permission to do this.");
                return;
            }
            Horse horse = (Horse) player.getVehicle();
            String metadataValue = plugin.getEntityMetadata(horse, PegasusTameListener.LEVITATE_KEY);
            if ("true".equals(metadataValue)){
                // Apply levitation effect to the horse
                horse.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 160, 1)); // 100 ticks = 5 seconds
                player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 160, 1)); // 100 ticks = 5 seconds

                // Schedule a task to apply fall damage immunity after Levitation ends
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        // Check if the horse and player still have the levitation effect
                        if (!horse.hasPotionEffect(PotionEffectType.LEVITATION)) {
                            // Apply fall damage immunity to the horse and player
                            horse.setMetadata("fallDamageImmune", new FixedMetadataValue(plugin, true));
                            player.setMetadata("fallDamageImmune", new FixedMetadataValue(plugin, true));

                            // Remove fall damage immunity after a duration
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    horse.removeMetadata("fallDamageImmune", plugin);
                                    player.removeMetadata("fallDamageImmune", plugin);
                                }
                            }.runTaskLater(plugin, 100); // 100 ticks = 5 seconds
                        }
                    }
                }.runTaskLater(plugin, getRemainingLevitationDuration(player) + 1); // Schedule the task after levitation duration ends
                // Schedule a repeating task to spawn particles while levitating
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (!horse.hasPotionEffect(PotionEffectType.LEVITATION)) {
                            this.cancel(); // Cancel the task if the horse is no longer levitating
                            return;
                        }
                        // Spawn particles at the horse's location
                        horse.getWorld().spawnParticle(Particle.SPELL, horse.getLocation().add(0, 1, 0), 10, 0.5, 0.5, 0.5, 0.05);
                    }
                }.runTaskTimer(plugin, 0, 5); // Run task every 5 ticks (0.25 seconds)
            }
        }
    }
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            Entity entity = event.getEntity();
            if (entity.hasMetadata("fallDamageImmune")) {
                event.setCancelled(true); // Cancel the fall damage
            }
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
