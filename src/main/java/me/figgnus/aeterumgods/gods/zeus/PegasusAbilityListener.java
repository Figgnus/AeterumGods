package me.figgnus.aeterumgods.gods.zeus;

import me.figgnus.aeterumgods.AeterumGods;
import me.figgnus.aeterumgods.items.CustomItems;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
                horse.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 200, 1)); // 100 ticks = 5 seconds
                player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 200, 1)); // 100 ticks = 5 seconds

                // Schedule a task to apply Slow Falling after Levitation ends
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        // Check if the horse still has the levitation effect
                        if (!horse.hasPotionEffect(PotionEffectType.LEVITATION)) {
                            // Apply Slow Falling effect to the horse
                            horse.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 100, 1)); // 200 ticks = 10 seconds
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 100, 1));

                        }
                    }
                }.runTaskLater(plugin, getRemainingLevitationDuration(player) + 1); // 100 ticks = 5 seconds (same as Levitation duration)
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
