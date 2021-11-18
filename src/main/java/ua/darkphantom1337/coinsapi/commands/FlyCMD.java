package ua.darkphantom1337.coinsapi.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ua.darkphantom1337.coinsapi.Main;
import ua.darkphantom1337.coinsapi.MySQL_Donate;

public class FlyCMD implements CommandExecutor {

    private Main plugin;

    public FlyCMD(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("coinsdonate.fly") || MySQL_Donate.getFly(p.getName())) {
                if (p.isFlying()) {
                    p.setFlying(false);
                    p.setAllowFlight(false);
                    p.sendMessage("§a[FLY] §fРежим полёта выключен.");
                } else {
                    p.setAllowFlight(true);
                    p.setFlying(true);
                    p.sendMessage("§a[FLY] §fРежим полёта включён.");
                }
            } else {
                p.sendMessage("§cУ вас нет доступа к данной команде.");
            }
        }
        return true;
    }

}
