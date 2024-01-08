package ru.yuiopmju.fchecks;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import ru.yuiopmju.fchecks.commands.CheckCommand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class FChecks extends JavaPlugin {

    private static final List<Check> checks = new ArrayList<>();

    @Override
    public void onEnable() {
        FileConfiguration config = this.getConfig();
        config.options().copyDefaults(true);
        try {
            config.save(this.getDataFolder().toString() + "/config.yml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        new CheckCommand(this);
        this.getServer().getPluginManager().registerEvents(new EventListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static boolean isCheck(String target){
        for(Check check : checks){
            if(check.getTarget().equals(target)){
                return true;
            }
        }
        return false;
    }

    public static boolean start(Check check){
        for(Check c : checks){
            if(c.getTarget().equals(check.getTarget()) || c.getSender().equals(check.getSender())){
                return false;
            }
        }
        check.sendMessages();
        checks.add(check);
        return true;
    }

    public static void stop(String sender){
        for(Check c : checks){
            if(c.getSender().equals(sender)){
                checks.remove(c);
                return;
            } else {
                Bukkit.getLogger().info(c.getSender() + " != " + sender);
            }
            // TODO: Didn't works.
        }

    }
}
