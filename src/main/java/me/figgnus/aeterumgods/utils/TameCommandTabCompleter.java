package me.figgnus.aeterumgods.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class TameCommandTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return List.of("poseidon", "zeus", "hades", "demeter", "hermes", "dionysus", "zeuslevitate");
    }
}
