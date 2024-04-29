package me.figgnus.aeterumgods.hades;

import me.figgnus.aeterumgods.AeterumGods;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NightVisionListener implements Listener {
    private final AeterumGods plugin;

    public NightVisionListener(AeterumGods plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Block block = player.getLocation().getBlock();

        if (player.hasPermission("aeterumgods.hades")){
            if(player.hasPermission("aeterumgods.hades.nightvision")){
                if (isUnderground(player) && isLowLightLevel(block)) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 400, 1, true, false));

                }else {
                    player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                }
            }
        }
    }
    private boolean isUnderground(Player player) {
        return player.getLocation().getBlockY() < 0;
    }
    private boolean isLowLightLevel(Block block){
        if(block.getLightLevel() < 3){

            return true;
        }
        return false;
    }



}
