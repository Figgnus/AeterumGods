package me.figgnus.aeterumgods.gods.demeter;

import me.figgnus.aeterumgods.AeterumGods;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;


public class GrowthPotionListener implements Listener {
    private final AeterumGods plugin;

    public GrowthPotionListener(AeterumGods plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerConsume(PlayerItemConsumeEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item == null)return;
        if (item.getItemMeta() == null)return;
        if (!item.hasItemMeta())return;
        
        if (item.getType() == Material.POTION && item.getItemMeta().getCustomModelData() == 114){
            if (!player.hasPermission("3")){
                player.sendMessage(ChatColor.RED + "You don't have permission to do this.");
                return;
            }
            applyGrowthEffect(player);
        }
    }

    private void applyGrowthEffect(Player player) {
        int totalStages = 5;
        // Apply custom effect to grow plants around the player
        new GrowthTask(plugin, player, totalStages).runTaskTimer(plugin, 0, 20); // Runs every second for 5 seconds
    }
}
class GrowthTask extends BukkitRunnable{

    private final AeterumGods plugin;
    private final Player player;
    private final int totalStages;
    private int counter = 0;

    public GrowthTask(AeterumGods plugin, Player player, int totalStages){
        this.plugin = plugin;
        this.player = player;
        this.totalStages = totalStages;
    }

    @Override
    public void run() {
        if (counter >= 5) {
            this.cancel();
            return;
        }
        growPlantsAroundPlayer(player);
        counter++;
    }

    private void growPlantsAroundPlayer(Player player) {
        World world = player.getWorld();
        Location location = player.getLocation();

        for (int x = -5; x <= 5; x++) {
            for (int z = -5; z <= 5; z++) {
                for (int y = -1; y <= 1; y++) {
                    Block block = world.getBlockAt(location.clone().add(x, y, z));
                    if (isGrowablePlant(block.getType())) {
                        incrementPlantGrowthStage(block);
                    }
                }
            }
        }
    }

    private void incrementPlantGrowthStage(Block block) {
        if (block.getBlockData() instanceof Ageable) {
            Ageable ageable = (Ageable) block.getBlockData();
            int newAge = ageable.getAge() + 1;
            if (newAge > ageable.getMaximumAge()) {
                newAge = ageable.getMaximumAge();
            }
            ageable.setAge(newAge);
            block.setBlockData(ageable);

            // Add particle effect
            block.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, block.getLocation().add(0.5, 0.5, 0.5), 10, 0.3, 0.3, 0.3, 0);
        }
    }

    private boolean isGrowablePlant(Material material) {
        return material == Material.WHEAT || material == Material.CARROTS || material == Material.POTATOES || material == Material.BEETROOTS;
    }
}