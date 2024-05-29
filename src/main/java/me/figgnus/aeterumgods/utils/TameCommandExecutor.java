package me.figgnus.aeterumgods.utils;

import me.figgnus.aeterumgods.items.CustomItems;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TameCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("Only players can use this command.");
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0){
            player.sendMessage("Usage: /tame <god>");
            return true;
        }
        String godName = args[0].toLowerCase();
        switch (godName){
            case "hades":
                player.getInventory().addItem(CustomItems.createHadesTameItem());
                break;
            case "poseidon":
                player.getInventory().addItem(CustomItems.cretatePoseidonTameItem());
                break;
            case "zeus":
                player.getInventory().addItem(CustomItems.createZeusTameItem());
                break;
            case "demeter":
                player.getInventory().addItem(CustomItems.createDemeterTameItem());
                break;
            case "hermes":
                player.getInventory().addItem(CustomItems.createHermesTameItem());
                break;
            case "dionysus":
                player.getInventory().addItem(CustomItems.createDionysusTameItem());
                break;
            case "zeuslevitate":
                player.getInventory().addItem(CustomItems.createPegasusAbilityItem());
                break;
            case "hermesspeed":
                player.getInventory().addItem(CustomItems.createSpeedHorseAbilityItem());
                break;
            default:
                player.sendMessage("Unknown god: " + godName);
                break;
        }
        return true;
    }
}
