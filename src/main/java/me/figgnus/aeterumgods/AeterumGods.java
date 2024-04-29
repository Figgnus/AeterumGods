package me.figgnus.aeterumgods;

import me.figgnus.aeterumgods.hades.NightVisionCommand;
import me.figgnus.aeterumgods.hades.NightVisionListener;
import me.figgnus.aeterumgods.hermes.FlyingItemCommand;
import me.figgnus.aeterumgods.hermes.FlyingItemListener;
import me.figgnus.aeterumgods.poseidon.DolphinGraceListener;
import me.figgnus.aeterumgods.poseidon.TridentCommand;
import me.figgnus.aeterumgods.zeus.BreedingItemCommand;
import me.figgnus.aeterumgods.zeus.BreedingItemListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class AeterumGods extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new NightVisionListener(this),this);
        getServer().getPluginManager().registerEvents(new BreedingItemListener(this),this);
        getServer().getPluginManager().registerEvents(new FlyingItemListener(),this);
        getServer().getPluginManager().registerEvents(new DolphinGraceListener(this), this);


        getCommand("nightvision").setExecutor(new NightVisionCommand(this));
        getCommand("breeding").setExecutor(new BreedingItemCommand());
        getCommand("flying").setExecutor(new FlyingItemCommand());
        getCommand("trident").setExecutor(new TridentCommand());
        getCommand("dolphingrace").setExecutor(new DolphinGraceListener(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
