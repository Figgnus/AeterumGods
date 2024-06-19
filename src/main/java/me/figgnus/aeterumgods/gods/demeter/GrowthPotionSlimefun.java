package me.figgnus.aeterumgods.gods.demeter;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.figgnus.aeterumgods.AeterumGods;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class GrowthPotionSlimefun extends SlimefunItem implements Listener {
    private final AeterumGods plugin;
    public GrowthPotionSlimefun(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, AeterumGods plugin) {
        super(itemGroup, item, recipeType, recipe);
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        ItemStack consumedItem = event.getItem();
        if (isOurCustomItem(consumedItem)) {
            applyGrowthEffect(event.getPlayer());
        }
    }
    private boolean isOurCustomItem(ItemStack item) {
        // Check if the item is your custom Slimefun item
        return item != null && item.getItemMeta() != null && item.getItemMeta().hasDisplayName() &&
                item.getItemMeta().getDisplayName().equals(getItem().getItemMeta().getDisplayName());
    }

    private void applyGrowthEffect(Player player) {
        int totalStages = 5;
        // Apply custom effect to grow plants around the player
        new GrowthTask(plugin, player, totalStages).runTaskTimer(plugin, 0, 20); // Runs every second for 5 seconds
    }
}
