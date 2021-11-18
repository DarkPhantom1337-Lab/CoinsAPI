package ua.darkphantom1337.coinsapi.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ua.darkphantom1337.coinsapi.Main;

public class CSpawnCMD implements CommandExecutor {

    private Main plugin;

    public CSpawnCMD(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("coinsapi.cspawn") && sender instanceof Player){
            plugin.cfg.getCfgFile().set("SpawnLocation", ((Player)sender).getLocation());
            plugin.cfg.saveCfgFile();
            sender.sendMessage("§aТочка спавна успешно установлена.");
        }
        return true;
    }

}
