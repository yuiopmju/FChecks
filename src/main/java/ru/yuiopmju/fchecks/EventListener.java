package ru.yuiopmju.fchecks;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.checkerframework.checker.guieffect.qual.SafeEffect;

public class EventListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if(FChecks.isCheck(e.getPlayer().getName())){
            e.setCancelled(true);
        }
    }
}
