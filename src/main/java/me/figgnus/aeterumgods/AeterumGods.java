package me.figgnus.aeterumgods;

import me.figgnus.aeterumgods.gods.hades.NightVisionListener;
import me.figgnus.aeterumgods.gods.hades.ZombieHorseAbilityListener;
import me.figgnus.aeterumgods.gods.hades.ZombieHorseTameListener;
import me.figgnus.aeterumgods.gods.hermes.FlyingItemCommand;
import me.figgnus.aeterumgods.gods.hermes.FlyingItemListener;
import me.figgnus.aeterumgods.gods.hermes.SpeedBootsListener;
import me.figgnus.aeterumgods.gods.poseidon.CustomConduitListener;
import me.figgnus.aeterumgods.gods.poseidon.DolphinGraceListener;
import me.figgnus.aeterumgods.gods.poseidon.SeaHorseAbilityListener;
import me.figgnus.aeterumgods.gods.zeus.PegasusAbilityListener;
import me.figgnus.aeterumgods.gods.poseidon.SeaHorseTameListener;
import me.figgnus.aeterumgods.gods.zeus.BreedingItemCommand;
import me.figgnus.aeterumgods.gods.zeus.BreedingItemListener;
import me.figgnus.aeterumgods.gods.zeus.PegasusTameListener;
import me.figgnus.aeterumgods.utils.LevitationListener;
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


        getCommand("nightvision").setExecutor(nightVisionListener);
        getCommand("breeding").setExecutor(new BreedingItemCommand());
        getCommand("flying").setExecutor(new FlyingItemCommand());
        getCommand("dolphingrace").setExecutor(dolphinGraceListener);
        //getCommand("conduit").setExecutor(customConduitListener);
        getCommand("boots").setExecutor(new SpeedBootsListener());
        getCommand("seapotion").setExecutor(seaHorseListener);
        getCommand("feather").setExecutor(pegasusTameListener);
        getCommand("apple").setExecutor(zombieHorseTameListener);
        getCommand("levitate").setExecutor(pegasusAbilityListener);
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
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
