package me.figgnus.aeterumgods.utils;

import me.figgnus.aeterumgods.AeterumGods;
import me.figgnus.aeterumgods.gods.demeter.FlowerHorseTameListener;
import me.figgnus.aeterumgods.gods.dionysos.DrunkHorseTameListener;
import me.figgnus.aeterumgods.gods.hades.ZombieHorseTameListener;
import me.figgnus.aeterumgods.gods.hermes.SpeedHorseTameListener;
import me.figgnus.aeterumgods.gods.poseidon.SeaHorseTameListener;
import me.figgnus.aeterumgods.gods.zeus.PegasusTameListener;
import me.figgnus.aeterumgods.items.CustomItems;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class TameCommandExecutor implements CommandExecutor {
    private final AeterumGods plugin;
    private Random random;

    public TameCommandExecutor(AeterumGods plugin) {
        this.plugin = plugin;
        this.random = new Random();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("Only players can use this command.");
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0){
            player.sendMessage("Usage: /tame <god> or /tame spwan <name>");
            return true;
        }
        String action = args[0].toLowerCase();

        if (action.equals("spawn") && args.length > 1) {
            String horseType = args[1].toLowerCase();
            handleSpawnCommand(player, horseType);
        } else {
            handleGodCommand(player, action);
        }
        return true;
    }
    private void handleGodCommand(Player player, String godName) {
        switch (godName) {
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
            default:
                player.sendMessage("Unknown god: " + godName);
                break;
        }
    }
    private void handleSpawnCommand(Player player,  String horseType) {
        switch (horseType) {
            case "poseidon":
                spawnHorse(player, EntityType.HORSE, SeaHorseTameListener.FROST_WALKER_KEY, Horse.Color.BLACK);
                break;
            case "hades":
                spawnHorse(player, EntityType.ZOMBIE_HORSE, ZombieHorseTameListener.LAVA_WALKER, null);
                break;
            case "zeus":
                spawnHorse(player, EntityType.HORSE, PegasusTameListener.LEVITATE_KEY, Horse.Color.WHITE);
                break;
            case "demeter":
                spawnHorse(player, EntityType.HORSE, FlowerHorseTameListener.SEED_KEY, Horse.Color.GRAY);
                break;
            case "hermes":
                spawnHorse(player, EntityType.HORSE, SpeedHorseTameListener.SPEED_KEY, Horse.Color.CREAMY);
                break;
            case "dionysus":
                spawnHorse(player, EntityType.HORSE, DrunkHorseTameListener.DRUNK_KEY, Horse.Color.CHESTNUT);
                break;
            default:
                player.sendMessage("Unknown horse type: " + horseType);
                break;
        }
    }
    private void spawnHorse(Player player, EntityType entityType, String metaKey, Horse.Color color) {
        Entity entity = player.getWorld().spawnEntity(player.getLocation(), entityType);
        if (entity instanceof Horse) {
            Horse horse = (Horse) entity;
            horse.setOwner(player);
            horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
            plugin.setEntityMetadata(horse, metaKey, "true");

            double speed = random.nextDouble(0.3, 0.3375);
            double jump = random.nextDouble(0.9, 1.1);
            int health = random.nextInt(25, 30);

            if (color != null) {
                horse.setColor(color);
            }

            horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);
            horse.getAttribute(Attribute.HORSE_JUMP_STRENGTH).setBaseValue(jump);
            horse.setMaxHealth(health);
            horse.setHealth(health);

            player.sendMessage(ChatColor.GREEN + "A " + horse.getCustomName() + " has been spawned!");
        } else if (entity instanceof ZombieHorse) {
            ZombieHorse horse = (ZombieHorse) entity;
            horse.setOwner(player);
            horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
            plugin.setEntityMetadata(horse, metaKey, "true");

            double speed = random.nextDouble(0.3, 0.3375);
            double jump = random.nextDouble(0.9, 1.1);
            int health = random.nextInt(25, 30);

            horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);
            horse.getAttribute(Attribute.HORSE_JUMP_STRENGTH).setBaseValue(jump);
            horse.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION, 1));
            horse.setMaxHealth(health);
            horse.setHealth(health);

            player.sendMessage(ChatColor.GREEN + "A " + horse.getCustomName() + " has been spawned!");
        }
    }
}
