package me.figgnus.aeterumgods;

import me.figgnus.aeterumgods.hades.NightVisionListener;
import me.figgnus.aeterumgods.hermes.FlyingItemCommand;
import me.figgnus.aeterumgods.hermes.FlyingItemListener;
import me.figgnus.aeterumgods.hermes.SpeedBootsListener;
import me.figgnus.aeterumgods.poseidon.CustomConduitListener;
import me.figgnus.aeterumgods.poseidon.DolphinGraceListener;
import me.figgnus.aeterumgods.poseidon.HorseTameListener;
import me.figgnus.aeterumgods.zeus.BreedingItemCommand;
import me.figgnus.aeterumgods.zeus.BreedingItemListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class AeterumGods extends JavaPlugin {

    @Override
    public void onEnable() {
        DolphinGraceListener dolphinGraceListener = new DolphinGraceListener();
        NightVisionListener nightVisionListener = new NightVisionListener();
        CustomConduitListener customConduitListener = new CustomConduitListener();
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(nightVisionListener,this);
        getServer().getPluginManager().registerEvents(new BreedingItemListener(this),this);
        getServer().getPluginManager().registerEvents(new FlyingItemListener(),this);
        getServer().getPluginManager().registerEvents(dolphinGraceListener, this);
        getServer().getPluginManager().registerEvents(new HorseTameListener(), this);
        //getServer().getPluginManager().registerEvents(customConduitListener, this);
        getServer().getPluginManager().registerEvents(new SpeedBootsListener(), this);

        getCommand("nightvision").setExecutor(nightVisionListener);
        getCommand("breeding").setExecutor(new BreedingItemCommand());
        getCommand("flying").setExecutor(new FlyingItemCommand());
        getCommand("dolphingrace").setExecutor(dolphinGraceListener);
        //getCommand("conduit").setExecutor(customConduitListener);
        getCommand("boots").setExecutor(new SpeedBootsListener());
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
