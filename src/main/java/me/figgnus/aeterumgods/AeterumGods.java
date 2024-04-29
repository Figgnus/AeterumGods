package me.figgnus.aeterumgods;

import me.figgnus.aeterumgods.hades.NightVisionListener;
import me.figgnus.aeterumgods.hermes.FlyingItemCommand;
import me.figgnus.aeterumgods.hermes.FlyingItemListener;
import me.figgnus.aeterumgods.poseidon.DolphinGraceListener;
import me.figgnus.aeterumgods.zeus.BreedingItemCommand;
import me.figgnus.aeterumgods.zeus.BreedingItemListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class AeterumGods extends JavaPlugin {

    @Override
    public void onEnable() {
        DolphinGraceListener dolphinGraceListener = new DolphinGraceListener();
        NightVisionListener nightVisionListener = new NightVisionListener();
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(nightVisionListener,this);
        getServer().getPluginManager().registerEvents(new BreedingItemListener(this),this);
        getServer().getPluginManager().registerEvents(new FlyingItemListener(),this);
        getServer().getPluginManager().registerEvents(dolphinGraceListener, this);


        getCommand("nightvision").setExecutor(nightVisionListener);
        getCommand("breeding").setExecutor(new BreedingItemCommand());
        getCommand("flying").setExecutor(new FlyingItemCommand());
        getCommand("dolphingrace").setExecutor(dolphinGraceListener);
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
