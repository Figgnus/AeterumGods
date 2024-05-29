package me.figgnus.aeterumgods;

import me.figgnus.aeterumgods.gods.demeter.FlowerHorseAbilityListener;
import me.figgnus.aeterumgods.gods.demeter.FlowerHorseTameListener;
import me.figgnus.aeterumgods.gods.hades.NightVisionListener;
import me.figgnus.aeterumgods.gods.hades.ZombieHorseAbilityListener;
import me.figgnus.aeterumgods.gods.hades.ZombieHorseTameListener;
import me.figgnus.aeterumgods.gods.hermes.FlyingItemListener;
import me.figgnus.aeterumgods.gods.hermes.SpeedBootsListener;
import me.figgnus.aeterumgods.gods.hermes.SpeedHorseAbilityListener;
import me.figgnus.aeterumgods.gods.hermes.SpeedHorseTameListener;
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
        SeaHorseTameListener seaHorseListener = new SeaHorseTameListener(this);
        SeaHorseAbilityListener seaHorseAbilityListener = new SeaHorseAbilityListener(this);
        ZombieHorseTameListener zombieHorseTameListener = new ZombieHorseTameListener(this);
        PegasusTameListener pegasusTameListener = new PegasusTameListener(this);
        PegasusAbilityListener pegasusAbilityListener = new PegasusAbilityListener(this);
        LevitationListener levitationListener = new LevitationListener(this);
        ZombieHorseAbilityListener zombieHorseAbilityListener = new ZombieHorseAbilityListener(this);
        FlowerHorseTameListener flowerHorseTameListener = new FlowerHorseTameListener(this);
        FlowerHorseAbilityListener flowerHorseAbilityListener = new FlowerHorseAbilityListener(this);
        BreedingItemListener breedingItemListener = new BreedingItemListener(this);
        FlyingItemListener flyingItemListener = new FlyingItemListener();
        SpeedBootsListener speedBootsListener = new SpeedBootsListener();
        SpeedHorseTameListener speedHorseTameListener = new SpeedHorseTameListener(this);
        SpeedHorseAbilityListener speedHorseAbilityListener = new SpeedHorseAbilityListener(this);
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(nightVisionListener,this);
        getServer().getPluginManager().registerEvents(breedingItemListener,this);
        getServer().getPluginManager().registerEvents(flyingItemListener,this);
        getServer().getPluginManager().registerEvents(dolphinGraceListener, this);
        getServer().getPluginManager().registerEvents(seaHorseListener, this);
        getServer().getPluginManager().registerEvents(speedBootsListener, this);
        getServer().getPluginManager().registerEvents(zombieHorseTameListener, this);
        getServer().getPluginManager().registerEvents(pegasusAbilityListener, this);
        getServer().getPluginManager().registerEvents(levitationListener, this);
        getServer().getPluginManager().registerEvents(pegasusTameListener, this);
        getServer().getPluginManager().registerEvents(seaHorseAbilityListener, this);
        getServer().getPluginManager().registerEvents(zombieHorseAbilityListener, this);
        getServer().getPluginManager().registerEvents(flowerHorseTameListener, this);
        getServer().getPluginManager().registerEvents(flowerHorseAbilityListener, this);
        getServer().getPluginManager().registerEvents(speedHorseTameListener, this);
        getServer().getPluginManager().registerEvents(speedHorseAbilityListener, this);

        // tab completers
        getCommand("tame").setTabCompleter(new TameCommandTabCompleter());
        getCommand("ag").setTabCompleter(new ItemCommandTabCompleter());

        getCommand("tame").setExecutor(new TameCommandExecutor());
        getCommand("ag").setExecutor(new ItemCommandExecutor());
        getCommand("nightvision").setExecutor(nightVisionListener);
        getCommand("dolphingrace").setExecutor(dolphinGraceListener);

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
