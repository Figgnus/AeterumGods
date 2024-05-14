package me.figgnus.aeterumgods.gods.poseidon;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashSet;
import java.util.Set;

import static org.bukkit.Bukkit.getLogger;

public class CustomConduitListener implements Listener, CommandExecutor {
    private Set<Block> activeConduits = new HashSet<>();

    //!!! currently not active


    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlockPlaced();
        // Check if the block placed is, for example, a SEA LANTERN and is waterlogged
        if (block.getType() == Material.CONDUIT && block.getBlockData() instanceof Waterlogged) {
            Waterlogged waterlogged = (Waterlogged) block.getBlockData();
            if (waterlogged.isWaterlogged()) {
                activeConduits.add(block);
                getLogger().info("Custom Conduit placed underwater!");
            }
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        for (Block block : activeConduits) {
            if (block.getLocation().distance(event.getPlayer().getLocation()) < 10) { // 10 blocks radius
                // Apply effects similar to a conduit
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 100, 0, true, true));
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!(sender instanceof Player)){
            sender.sendMessage("This command can only be performed by player.");
        }
        Player player = (Player) sender;
        if (!player.hasPermission("aeterumgods.conduit.admin")){
            player.sendMessage(ChatColor.RED + "You don't have permission use this command.");
        }
        player.getInventory().addItem(createCustomBlock());
        return true;
    }

    private ItemStack createCustomBlock() {
        ItemStack conduit = new ItemStack(Material.CONDUIT);
        ItemMeta meta = conduit.getItemMeta();
        meta.setCustomModelData(101);
        meta.setDisplayName("Mini Conduit");
        conduit.setItemMeta(meta);
        return conduit;
    }
}
