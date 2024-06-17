package me.figgnus.aeterumgods.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class ItemCommandTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return List.of("breeding", "flying", "boots", "zeuslevitate", "hermesspeed", "randomizer", "bonemeal", "growth");
    }
}
