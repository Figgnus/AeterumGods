package me.figgnus.aeterumgods.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CustomItems {
    private static final List<CustomItem> customItems = new ArrayList<>();
    public static class CustomItem {
        private final String name;
        private final int customModelData;
        private final ItemStack item;

        public CustomItem(String name, int customModelData, ItemStack item) {
            this.name = name;
            this.customModelData = customModelData;
            this.item = item;
        }

        public String getName() {
            return name;
        }

        public int getCustomModelData() {
            return customModelData;
        }

        public ItemStack getItem() {
            return item;
        }
    }
    public static List<CustomItem> getCustomItems() {
        return customItems;
    }
    // Custom Conduit
    public static ItemStack createCustomConduit() {
        ItemStack conduit = new ItemStack(Material.CONDUIT);
        ItemMeta meta = conduit.getItemMeta();
        meta.setCustomModelData(101);
        meta.setDisplayName("Mini Conduit");
        conduit.setItemMeta(meta);

        customItems.add(new CustomItem("Mini Conduit", 101, conduit));
        return conduit;
    }
    // Flying Item for Hermes
    public static ItemStack createFlyingItem() {
        ItemStack customSword = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = customSword.getItemMeta();

        meta.setCustomModelData(102);
        meta.setDisplayName(ChatColor.GREEN + "Fly! Forrest Fly!");
        customSword.setItemMeta(meta);

        customItems.add(new CustomItem("Fly! Forrest Fly!", 102, customSword));
        return customSword;
    }
    // Breeding item for Zeus
    public static ItemStack createBreedingItem(){
        ItemStack breedingItem = new ItemStack(Material.SLIME_BALL);
        ItemMeta breedingItemMeta = breedingItem.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemLore.add("This Egg lets you breed animals");

        breedingItemMeta.setCustomModelData(103);
        breedingItemMeta.setDisplayName(ChatColor.GREEN + "Magic Egg");
        breedingItemMeta.setLore(itemLore);
        breedingItem.setItemMeta(breedingItemMeta);

        customItems.add(new CustomItem("Magic Egg", 103, breedingItem));
        return breedingItem;
    }
    // Custom Boots for Hermes
    public static ItemStack createCustomBoots() {
        ItemStack boots = new ItemStack(Material.IRON_BOOTS);
        ItemMeta meta = boots.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Boots of Speed");
        meta.setCustomModelData(104);
        boots.setItemMeta(meta);

        customItems.add(new CustomItem("Boots of Speed", 104, boots));
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

        customItems.add(new CustomItem("Poseidon's Potion", 105, item));
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

        customItems.add(new CustomItem("Hades's Potion", 106, item));
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

        customItems.add(new CustomItem("Levitate Potion", 107, customItem));
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

        customItems.add(new CustomItem("Zeus's Potion", 108, item));
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

        customItems.add(new CustomItem("Demeter's Potion", 109, item));
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

        customItems.add(new CustomItem("Hermes's Potion", 110, item));
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

        customItems.add(new CustomItem("Dionysus's Potion", 111, item));
        return item;
    }
    // Item for speed horse ability
    public static ItemStack createSpeedHorseAbilityItem(){
        ItemStack item = new ItemStack(Material.POTION);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(112);
        meta.setDisplayName(ChatColor.GREEN + "Horse Speed Potion");
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        item.setItemMeta(meta);

        customItems.add(new CustomItem("Horse Speed Potion", 112, item));
        return item;
    }
    // Item for Demeter (bonemeal for cactus / sugar cane)
    public static ItemStack createBetterBonemeal(){
        ItemStack item = new ItemStack(Material.SUGAR);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.GREEN + "Super Bonemeal");
        meta.setCustomModelData(113);

        item.setItemMeta(meta);

        customItems.add(new CustomItem("Super Bonemeal", 113, item));
        return item;
    }
    public static ItemStack createRandomizerItem() {
        ItemStack randomiser = new ItemStack(Material.DIAMOND_SHOVEL);
        ItemMeta randomiserMeta = randomiser.getItemMeta();

        randomiserMeta.setDisplayName(ChatColor.GREEN + "Randomizer");
        randomiserMeta.setCustomModelData(1234567);
        randomiser.setItemMeta(randomiserMeta);

        customItems.add(new CustomItem("Randomizer", 1234567, randomiser));
        return randomiser;
    }
}
