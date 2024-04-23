package me.figgnus.aeterumgods.poseidon;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PosseidonListener implements Listener {
    @EventHandler
    public void onWaterEnter(PlayerMoveEvent event){
        Player player = event.getPlayer();

    }
}
