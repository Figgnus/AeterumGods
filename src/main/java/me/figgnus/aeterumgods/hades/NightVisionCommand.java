package me.figgnus.aeterumgods.hades;

import me.figgnus.aeterumgods.AeterumGods;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;
import java.util.UUID;

public class NightVisionCommand implements CommandExecutor {
    private final AeterumGods plugin;
    HashMap<UUID, PermissionAttachment> perms = new HashMap<>();
    public NightVisionCommand(AeterumGods plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            String permission = "aeterumgods.hades.nightvision";


            if (player.hasPermission("aeterumgods.hades")){
                if(player.hasPermission(permission)){
                    removePermission(player);
                    player.sendMessage(ChatColor.DARK_PURPLE + "Night Vision is turned OFF");
                }else if (!player.hasPermission(permission)) {
                    addPermission(player);
                    player.sendMessage(ChatColor.DARK_PURPLE + "Night Vision is turned ON");
                }else {
                    player.sendMessage(ChatColor.RED + "Something went wrong");
                }
            }else {
                player.sendMessage(ChatColor.RED + "You don't have permission to do this");
            }
        }
        return true;
    }
    public void removePermission(Player player) {
        perms.get(player.getUniqueId()).unsetPermission("aeterumgods.hades.nightvision");
    }
    public void addPermission(Player player){
        org.bukkit.permissions.PermissionAttachment attachment = player.addAttachment(plugin);
        perms.put(player.getUniqueId(), attachment);
        attachment.setPermission("aeterumgods.hades.nightvision", true);
    }

}
