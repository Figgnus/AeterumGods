package me.figgnus.aeterumgods.utils;

import com.dre.brewery.recipe.CustomItem;
import me.figgnus.aeterumgods.items.CustomItems;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ItemCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("Only players can use this command.");
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0){
            player.sendMessage("Usage: /ag <item>");
            return true;
        }
        String item = args[0].toLowerCase();
        switch (item){
            case "breeding":
                player.getInventory().addItem(CustomItems.createBreedingItem());
                break;
            case "flying":
                player.getInventory().addItem(CustomItems.createFlyingItem());
                break;
            case "boots":
                player.getInventory().addItem(CustomItems.createCustomBoots());
                break;
            case "zeuslevitate":
                player.getInventory().addItem(CustomItems.createPegasusAbilityItem());
                break;
            case "hermesspeed":
                player.getInventory().addItem(CustomItems.createSpeedHorseAbilityItem());
                break;
            case "randomizer":
                player.getInventory().addItem(CustomItems.createRandomizerItem());
                break;
            case "bonemeal":
                player.getInventory().addItem(CustomItems.createBetterBonemeal());
                break;
            case "growth":
                player.getInventory().addItem(CustomItems.createGrowthPotion());
                break;
            default:
                player.sendMessage("Unknown item: " + item);
                break;
        }
        return true;
    }
}
