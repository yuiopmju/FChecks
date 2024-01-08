package ru.yuiopmju.fchecks.commands;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.yuiopmju.fchecks.Check;
import ru.yuiopmju.fchecks.FChecks;

import java.util.List;

public class CheckCommand implements CommandExecutor, TabCompleter {
    private static final FileConfiguration config = FChecks.getPlugin(FChecks.class).getConfig();
    public CheckCommand(JavaPlugin plugin){
        plugin.getCommand("fchecks").setExecutor(this);
        plugin.getCommand("fchecks").setTabCompleter(this);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission(config.getString("fcheck-permission"))){
            sender.sendMessage(config.getString("messages.no-permissions"));
            return true;
        }
        if(args.length == 0){
            sender.sendMessage(config.getString("messages.invalid-args"));
            return true;
        }
        if(!(sender instanceof Player)){
            sender.sendMessage("§cYou must to be a player!");
            return true;
        }
        String subcmd = args[0].toLowerCase();
        if(subcmd.equals("check")){
            if(args[1].equalsIgnoreCase("start")){
                 if(args.length != 3){
                     sender.sendMessage(config.getString("messages.invalid-args"));
                     return true;
                  }
               if(Bukkit.getPlayer(args[2]) == null){
                  sender.sendMessage(config.getString("messages.player-not-found"));
                  return true;
              }
              if(FChecks.start(new Check(sender.getName(), args[2]))){
                    sender.sendMessage(config.getString("messages.check-started"));
                    return true;
                }
            } else if(args[1].equalsIgnoreCase("stop")){
                if (args.length == 2) {
                    FChecks.stop(sender.getName());
                    return true;
                } else {
                    sender.sendMessage(config.getString("messages.check-started"));
                    return true;
                }
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission(config.getString("fcheck-permission"))) return null;
        if(args.length == 1){
            return Lists.newArrayList("check");
        } else if(args.length == 2 && args[0].equalsIgnoreCase("check")){
            return Lists.newArrayList("start", "stop");
        }
        return null;
    }
}
