package ua.darkphantom1337.coinsapi.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ua.darkphantom1337.coinsapi.Main;
import ua.darkphantom1337.coinsapi.entitys.DarkPlayer;

public class ShopCMD implements CommandExecutor {

    private Main plugin;

    public ShopCMD(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {
        Main.getInstance().donateGUI.openDonateGUI((Player) sender);
        return true;
    }

}
