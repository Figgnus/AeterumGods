package me.figgnus.aeterumgods.poseidon;

import me.figgnus.aeterumgods.AeterumGods;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.UUID;

public class DolphinGraceListener implements Listener, CommandExecutor {
    private final AeterumGods plugin;
    HashMap<UUID, PermissionAttachment> perms = new HashMap<>();

    public DolphinGraceListener(AeterumGods plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onWaterEnter(PlayerMoveEvent event){
        Player player = event.getPlayer();
        if (player.hasPermission("aeterumgods.dolphingrace.use")){
            if (player.getLocation().getBlock().getType().name().contains("WATER")){
                player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 100, 1, true, true));
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "This command can be only used by players");
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("aeterumgods.dolphingrace.toggle")){
            player.sendMessage(ChatColor.RED + " You don't have permission to toggle Dolphin's Grace.");
            return true;
        }
        String permission = "aeterumgods.dolphingrace.use";

        if(player.hasPermission(permission)){
            removePermission(player);
            player.sendMessage(ChatColor.DARK_PURPLE + "Dolphin's grace disabled");
        }else if (!player.hasPermission(permission)) {
            addPermission(player);
            player.sendMessage(ChatColor.DARK_PURPLE + "Dolphin's grace enabled");
        }else {
            player.sendMessage(ChatColor.RED + "Something went wrong");
        }
        return true;
    }
    public void removePermission(Player player) {
        perms.get(player.getUniqueId()).unsetPermission("aeterumgods.dolphingrace.use");
    }
    public void addPermission(Player player){
        org.bukkit.permissions.PermissionAttachment attachment = player.addAttachment(plugin);
        perms.put(player.getUniqueId(), attachment);
        attachment.setPermission("aeterumgods.dolphingrace.use", true);
    }
}
