package me.figgnus.aeterumgods;

import me.figgnus.aeterumgods.gods.demeter.FlowerHorseAbilityListener;
import me.figgnus.aeterumgods.gods.demeter.FlowerHorseTameListener;
import me.figgnus.aeterumgods.gods.hades.NightVisionListener;
import me.figgnus.aeterumgods.gods.hades.ZombieHorseAbilityListener;
import me.figgnus.aeterumgods.gods.hades.ZombieHorseTameListener;
import me.figgnus.aeterumgods.gods.hermes.FlyingItemListener;
import me.figgnus.aeterumgods.gods.hermes.SpeedBootsListener;
import me.figgnus.aeterumgods.gods.poseidon.DolphinGraceListener;
import me.figgnus.aeterumgods.gods.poseidon.SeaHorseAbilityListener;
import me.figgnus.aeterumgods.gods.zeus.PegasusAbilityListener;
import me.figgnus.aeterumgods.gods.poseidon.SeaHorseTameListener;
import me.figgnus.aeterumgods.gods.zeus.BreedingItemListener;
import me.figgnus.aeterumgods.gods.zeus.PegasusTameListener;
import me.figgnus.aeterumgods.utils.*;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public final class AeterumGods extends JavaPlugin {

    @Override
    public void onEnable() {
        DolphinGraceListener dolphinGraceListener = new DolphinGraceListener();
        NightVisionListener nightVisionListener = new NightVisionListener();
        //CustomConduitListener customConduitListener = new CustomConduitListener();
        SeaHorseTameListener seaHorseListener = new SeaHorseTameListener(this);
        SeaHorseAbilityListener seaHorseAbilityListener = new SeaHorseAbilityListener(this);
        ZombieHorseTameListener zombieHorseTameListener = new ZombieHorseTameListener(this);
        PegasusTameListener pegasusTameListener = new PegasusTameListener(this);
        PegasusAbilityListener pegasusAbilityListener = new PegasusAbilityListener(this);
        LevitationListener levitationListener = new LevitationListener(this);
        ZombieHorseAbilityListener zombieHorseAbilityListener = new ZombieHorseAbilityListener(this);
        FlowerHorseTameListener flowerHorseTameListener = new FlowerHorseTameListener(this);
        FlowerHorseAbilityListener flowerHorseAbilityListener = new FlowerHorseAbilityListener(this);
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(nightVisionListener,this);
        getServer().getPluginManager().registerEvents(new BreedingItemListener(this),this);
        getServer().getPluginManager().registerEvents(new FlyingItemListener(),this);
        getServer().getPluginManager().registerEvents(dolphinGraceListener, this);
        getServer().getPluginManager().registerEvents(seaHorseListener, this);
        //getServer().getPluginManager().registerEvents(customConduitListener, this);
        getServer().getPluginManager().registerEvents(new SpeedBootsListener(), this);
        getServer().getPluginManager().registerEvents(zombieHorseTameListener, this);
        getServer().getPluginManager().registerEvents(pegasusAbilityListener, this);
        getServer().getPluginManager().registerEvents(levitationListener, this);
        getServer().getPluginManager().registerEvents(pegasusTameListener, this);
        getServer().getPluginManager().registerEvents(seaHorseAbilityListener, this);
        getServer().getPluginManager().registerEvents(zombieHorseAbilityListener, this);
        getServer().getPluginManager().registerEvents(flowerHorseTameListener, this);
        getServer().getPluginManager().registerEvents(flowerHorseAbilityListener, this);

        // tab completer for taming horses
        getCommand("tame").setTabCompleter(new TameCommandTabCompleter());
        // tab completer for items
        getCommand("ag").setTabCompleter(new ItemCommandTabCompleter());

        getCommand("tame").setExecutor(new TameCommandExecutor());
        getCommand("ag").setExecutor(new ItemCommandExecutor());
        getCommand("nightvision").setExecutor(nightVisionListener);
        getCommand("dolphingrace").setExecutor(dolphinGraceListener);
        //getCommand("conduit").setExecutor(customConduitListener);

    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    //methods for making metadata of entities persistent
    public void setEntityMetadata(Entity entity, String key, String value){
        NamespacedKey namespacedKey = new NamespacedKey(this, key);
        PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
        dataContainer.set(namespacedKey, PersistentDataType.STRING, value);
    }
    public String getEntityMetadata(Entity entity, String key) {
        NamespacedKey namespacedKey = new NamespacedKey(this, key);
        PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
        if (dataContainer.has(namespacedKey, PersistentDataType.STRING)) {
            return dataContainer.get(namespacedKey, PersistentDataType.STRING);
        }
        return null;
    }
}
