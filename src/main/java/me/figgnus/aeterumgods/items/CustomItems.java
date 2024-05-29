package me.figgnus.aeterumgods.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CustomItems {
    // Custom Conduit
    public static ItemStack createCustomConduit() {
        ItemStack conduit = new ItemStack(Material.CONDUIT);
        ItemMeta meta = conduit.getItemMeta();
        meta.setCustomModelData(101);
        meta.setDisplayName("Mini Conduit");
        conduit.setItemMeta(meta);
        return conduit;
    }
    // Flying Item for Hermes
    public static ItemStack createFlyingItem() {
        ItemStack customSword = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = customSword.getItemMeta();

        meta.setCustomModelData(102);
        meta.setDisplayName(ChatColor.GREEN + "Fly! Forrest Fly!");
        customSword.setItemMeta(meta);

        return customSword;
    }
    // Breeding item for Zeus
    public static ItemStack createBreedingItem(){
        ItemStack breedingItem = new ItemStack(Material.SLIME_BALL);
        ItemMeta breedingItemMeta = breedingItem.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemLore.add("This Egg lets you breed animals");

        breedingItemMeta.setCustomModelData(103);
        breedingItemMeta.setDisplayName(ChatColor.GOLD + "Magic Egg");
        breedingItemMeta.setLore(itemLore);
        breedingItem.setItemMeta(breedingItemMeta);

        return breedingItem;
    }
    // Custom Boots for Hermes
    public static ItemStack createCustomBoots() {
        ItemStack boots = new ItemStack(Material.IRON_BOOTS);
        ItemMeta meta = boots.getItemMeta();
        meta.setDisplayName("Boots of Speed");
        meta.setCustomModelData(104);
        boots.setItemMeta(meta);
        return boots;
    }
    // Item for taming Poseidon horse
    public static ItemStack cretatePoseidonTameItem() {
        ItemStack item = new ItemStack(Material.POTION);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(105);
        meta.setDisplayName(ChatColor.GREEN + "Poseidon's Potion");
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        item.setItemMeta(meta);

        return item;
    }
    // Item for taming Hades horse
    public static ItemStack createHadesTameItem() {
        ItemStack item = new ItemStack(Material.POTION);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(106);
        meta.setDisplayName(ChatColor.GREEN + "Hades's Potion");
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        item.setItemMeta(meta);

        return item;
    }
    // Item for using Pegasus ability
    public static ItemStack createPegasusAbilityItem(){
        ItemStack customItem = new ItemStack(Material.POTION);
        ItemMeta meta = customItem.getItemMeta();
        meta.setCustomModelData(107);
        meta.setDisplayName(ChatColor.GREEN + "Levitate Potion");
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        customItem.setItemMeta(meta);

        return customItem;
    }
    // Item for taming Zeus horse
    public static ItemStack createZeusTameItem() {
        ItemStack item = new ItemStack(Material.POTION);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(108);
        meta.setDisplayName(ChatColor.GREEN + "Zeus's Potion");
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        item.setItemMeta(meta);

        return item;
    }
    // Item for taming Demeter horse
    public static ItemStack createDemeterTameItem(){
        ItemStack item = new ItemStack(Material.POTION);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(109);
        meta.setDisplayName(ChatColor.GREEN + "Demeter's Potion");
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        item.setItemMeta(meta);

        return item;
    }
    // Item for taming Hermes horse
    public static ItemStack createHermesTameItem(){
        ItemStack item = new ItemStack(Material.POTION);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(110);
        meta.setDisplayName(ChatColor.GREEN + "Hermes's Potion");
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        item.setItemMeta(meta);

        return item;
    }
    // Item for taming Dionysus horse
    public static ItemStack createDionysusTameItem(){
        ItemStack item = new ItemStack(Material.POTION);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(111);
        meta.setDisplayName(ChatColor.GREEN + "Dionysus's Potion");
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        item.setItemMeta(meta);

        return item;
    }
}
