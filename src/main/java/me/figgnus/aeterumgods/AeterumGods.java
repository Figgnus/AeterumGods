package me.figgnus.aeterumgods;

import me.figgnus.aeterumgods.gods.hades.NightVisionListener;
import me.figgnus.aeterumgods.gods.hades.ZombieHorseTameListener;
import me.figgnus.aeterumgods.gods.hermes.FlyingItemCommand;
import me.figgnus.aeterumgods.gods.hermes.FlyingItemListener;
import me.figgnus.aeterumgods.gods.hermes.SpeedBootsListener;
import me.figgnus.aeterumgods.gods.poseidon.CustomConduitListener;
import me.figgnus.aeterumgods.gods.poseidon.DolphinGraceListener;
import me.figgnus.aeterumgods.gods.poseidon.HorseAbilityListener;
import me.figgnus.aeterumgods.gods.poseidon.HorseTameListener;
import me.figgnus.aeterumgods.gods.zeus.BreedingItemCommand;
import me.figgnus.aeterumgods.gods.zeus.BreedingItemListener;
import me.figgnus.aeterumgods.utils.LevitationListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class AeterumGods extends JavaPlugin {

    @Override
    public void onEnable() {
        DolphinGraceListener dolphinGraceListener = new DolphinGraceListener();
        NightVisionListener nightVisionListener = new NightVisionListener();
        CustomConduitListener customConduitListener = new CustomConduitListener();
        HorseTameListener horseTameListener = new HorseTameListener(this);
        ZombieHorseTameListener zombieHorseTameListener = new ZombieHorseTameListener(this);
        HorseAbilityListener horseAbilityListener = new HorseAbilityListener(this);
        LevitationListener levitationListener = new LevitationListener(this);
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(nightVisionListener,this);
        getServer().getPluginManager().registerEvents(new BreedingItemListener(this),this);
        getServer().getPluginManager().registerEvents(new FlyingItemListener(),this);
        getServer().getPluginManager().registerEvents(dolphinGraceListener, this);
        getServer().getPluginManager().registerEvents(horseTameListener, this);
        //getServer().getPluginManager().registerEvents(customConduitListener, this);
        getServer().getPluginManager().registerEvents(new SpeedBootsListener(), this);
        getServer().getPluginManager().registerEvents(zombieHorseTameListener, this);
        getServer().getPluginManager().registerEvents(horseAbilityListener, this);
        getServer().getPluginManager().registerEvents(levitationListener, this);

        getCommand("nightvision").setExecutor(nightVisionListener);
        getCommand("breeding").setExecutor(new BreedingItemCommand());
        getCommand("flying").setExecutor(new FlyingItemCommand());
        getCommand("dolphingrace").setExecutor(dolphinGraceListener);
        //getCommand("conduit").setExecutor(customConduitListener);
        getCommand("boots").setExecutor(new SpeedBootsListener());
        getCommand("feather").setExecutor(horseTameListener);
        getCommand("apple").setExecutor(zombieHorseTameListener);
        getCommand("levitate").setExecutor(horseAbilityListener);
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
