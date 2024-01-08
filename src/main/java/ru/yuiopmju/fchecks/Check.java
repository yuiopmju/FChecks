package ru.yuiopmju.fchecks;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Check {
    private Player sender;
    private Player target;
    private static final String messageToAlert = FChecks.getPlugin(FChecks.class).getConfig().getString("messages.check-start-alert");
    public Check(String sender, String target){
        if(Bukkit.getPlayer(sender) != null){
            if(Bukkit.getPlayer(target) != null){
                this.sender = Bukkit.getPlayer(sender);
                this.target = Bukkit.getPlayer(target);
            }
        }
    }

    public void start(){

    }

    public String getTarget(){
        return target.getName();
    }

    public String getSender(){
        return sender.getName();
    }

    public void sendMessages(){
        int i = 0;
        while(i != 10){
            target.sendMessage(messageToAlert);
            target.sendTitle(messageToAlert, null);
            target.sendActionBar(messageToAlert);
            target.playSound(target.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1.0F, 1.0F);
            i++;
        }
    }
}
