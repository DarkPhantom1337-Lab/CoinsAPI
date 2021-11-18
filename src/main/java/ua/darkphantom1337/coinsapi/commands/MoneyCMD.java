package ua.darkphantom1337.coinsapi.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ua.darkphantom1337.coinsapi.Main;
import ua.darkphantom1337.coinsapi.entitys.DarkPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MoneyCMD implements CommandExecutor {

    private Main plugin;

    public MoneyCMD(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0 && sender instanceof Player) {
            DarkPlayer darkPlayer = new DarkPlayer(sender.getName());
            darkPlayer.getBukkitPlayer().sendMessage(replaceValues(darkPlayer, Main.getInstance().cfg.getMessage("SeeMyInfo")));
            return true;
        }
        if (!sender.hasPermission("coinsapi.admin")) {
            sender.sendMessage("�c[�eCoinsAPI�c] �f-> �c������� �� ����������.");
            return false;
        }
        if (args.length == 2 && args[0].equals("bal")){
            sender.sendMessage("�a������ ������ " + args[1] + " ���������� " + Main.getInstance().getFormattedBalance(new DarkPlayer(args[1]).getBalance()));
            return true;
        }
        if (args.length == 2 && args[0].equals("lvl")){
            sender.sendMessage("�a������� ������ " + args[1] + " ���������� " + new DarkPlayer(args[1]).getLevel());
            return true;
        }
        if (args.length == 2 && args[0].equals("time")){
            sender.sendMessage("�a����� ������ " + args[1] + " ���������� " + new DarkPlayer(args[1]).getPlayedMinutes() + " �����.");
            return true;
        }
        if (args.length == 3) {
            try {
                if (args[0].equals("addBalance")) {
                    Double balance = Double.parseDouble(args[2]);
                    if (balance < 0)
                        balance *= -1;
                    new DarkPlayer(args[1]).giveBalance(balance);
                    sender.sendMessage("�a[�eCoinsAPI�a] �f-> �a� ������� ������ " + args[1] + " ���������� " + balance + " ������.");
                    return true;
                }
                if (args[0].equals("removeBalance")) {
                    Double balance = Double.parseDouble(args[2]);
                    if (balance < 0)
                        balance *= -1;
                    new DarkPlayer(args[1]).takeBalance(balance);
                    sender.sendMessage("�a[�eCoinsAPI�a] �f-> �a������ ������ " + args[1] + " �������� �� " + balance + " ������.");
                    return true;
                }
                if (args[0].equals("setBalance")) {
                    Double balance = Double.parseDouble(args[2]);
                    new DarkPlayer(args[1]).setBalance(balance);
                    sender.sendMessage("�a[�eCoinsAPI�a] �f-> �a������ ������ " + args[1] + " ���������� ��  " + balance + " ������.");
                    return true;
                }
                if (args[0].equals("givelvl")) {
                    Integer level = Integer.parseInt(args[2]);
                    if (level < 0)
                        level *= -1;
                    new DarkPlayer(args[1]).giveLevel(level);
                    sender.sendMessage("�a[�eCoinsAPI�a] �f-> �a� ������ ������ " + args[1] + " ���������� " + level + " �������(���).");
                    return true;
                }
                if (args[0].equals("takelvl")) {
                    Integer level = Integer.parseInt(args[2]);
                    if (level < 0)
                        level *= -1;
                    new DarkPlayer(args[1]).takeLevel(level);
                    sender.sendMessage("�a[�eCoinsAPI�a] �f-> �a������� ������ " + args[1] + " �������� �� " + level + " �������(���).");
                    return true;
                }
                if (args[0].equals("setlvl")) {
                    Integer level = Integer.parseInt(args[2]);
                    if (level <= 0)
                        level = 1;
                    new DarkPlayer(args[1]).setLevel(level);
                    sender.sendMessage("�a[�eCoinsAPI�a] �f-> �a������� ������ " + args[1] + " ���������� ��  " + level + " �������(���).");
                    return true;
                }
                if (args[0].equals("givePlayedMinutes")) {
                    Integer level = Integer.parseInt(args[2]);
                    if (level < 0)
                        level *= -1;
                    new DarkPlayer(args[1]).givePlayedMinutes(level);
                    sender.sendMessage("�a[�eCoinsAPI�a] �f-> �a� ������� ������ " + args[1] + " ���������� " + level + " �����.");
                    return true;
                }
                if (args[0].equals("takePlayedMinutes")) {
                    Integer level = Integer.parseInt(args[2]);
                    if (level < 0)
                        level *= -1;
                    new DarkPlayer(args[1]).takePlayedMinutes(level);
                    sender.sendMessage("�a[�eCoinsAPI�a] �f-> �a����� ������ " + args[1] + " ��������� �� " + level + " �����.");
                    return true;
                }
                if (args[0].equals("setPlayedMinutes")) {
                    Integer level = Integer.parseInt(args[2]);
                    new DarkPlayer(args[1]).setPlayedMinutes(level);
                    sender.sendMessage("�a[�eCoinsAPI�a] �f-> �a����� ������ " + args[1] + " ����������� ��  " + level + " �����.");
                    return true;
                }
            } catch (Exception e){
                sender.sendMessage("�a������ ��� ��������� �������. ��������-0=" + args[0] + " 1=" + args[1] + " 2="+args[2]);
                return true;
            }
        }
        sender.sendMessage("�c[�eCoinsAPI�c] �f-> �c������ ��������� �� ���������� ���� �� �� ������� �������������� ���������.");
        return false;
    }

    public String replaceValues(DarkPlayer darkPlayer, String toReplace) {
        if (toReplace.contains("%balance%"))
            toReplace = toReplace.replace("%balance%", Main.getInstance().getFormattedBalance(darkPlayer.getBalance()));
        if (toReplace.contains("%level%"))
            toReplace = toReplace.replace("%level%", darkPlayer.getLevel().toString());
        if (toReplace.contains("%hours%"))
            toReplace = toReplace.replace("%hours%", "" + Math.floor(darkPlayer.getPlayedMinutes() / 60));
        return toReplace;
    }

}
