package me.figgnus.aeterumgods.poseidon;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTameEvent;

public class HorseTameListener implements Listener {
    @EventHandler
    public void onHorseTame(EntityTameEvent event){
        if (event.getEntity() instanceof Horse && event.getOwner() instanceof Player) {
            Player player = (Player) event.getOwner();
            Horse horse = (Horse) event.getEntity();

            // Check if the player has the required permission
            if (player.hasPermission("aeterumgods.tame.use")) {
                // Set high horse speed and jump strength
                setHorseStats(horse, 0.3, 1.2); // Values can be adjusted
                player.sendMessage(ChatColor.GREEN + "Your newly tamed horse has enhanced abilities!");
            }
        }
    }
    private void setHorseStats(Horse horse, double speed, double jump) {
        AttributeInstance horseSpeed = horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        if (horseSpeed != null){
            horseSpeed.setBaseValue(speed);
        }
        AttributeInstance horseJump = horse.getAttribute(Attribute.HORSE_JUMP_STRENGTH);
        if (horseJump != null){
            horseJump.setBaseValue(jump);
        }
    }
}
