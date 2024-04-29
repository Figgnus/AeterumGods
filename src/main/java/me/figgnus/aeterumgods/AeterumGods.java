package me.figgnus.aeterumgods;

import me.figgnus.aeterumgods.hades.HadesCommands;
import me.figgnus.aeterumgods.hades.HadesListener;
import me.figgnus.aeterumgods.hermes.HermesCommand;
import me.figgnus.aeterumgods.hermes.HermesListener;
import me.figgnus.aeterumgods.poseidon.PosseidonListener;
import me.figgnus.aeterumgods.poseidon.TridentCommand;
import me.figgnus.aeterumgods.zeus.ZeusCommand;
import me.figgnus.aeterumgods.zeus.ZeusListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class AeterumGods extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new HadesListener(this),this);
        getServer().getPluginManager().registerEvents(new ZeusListener(this),this);
        getServer().getPluginManager().registerEvents(new HermesListener(),this);
        getServer().getPluginManager().registerEvents(new PosseidonListener(), this);


        getCommand("nightvision").setExecutor(new HadesCommands(this));
        getCommand("breeding").setExecutor(new ZeusCommand());
        getCommand("flying").setExecutor(new HermesCommand());
        getCommand("trident").setExecutor(new TridentCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
