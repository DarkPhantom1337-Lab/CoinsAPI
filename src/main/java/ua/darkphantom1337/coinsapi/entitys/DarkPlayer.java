package ua.darkphantom1337.coinsapi.entitys;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ua.darkphantom1337.coinsapi.Main;
import ua.darkphantom1337.coinsapi.MySQL;

public class DarkPlayer {

    private String playerName;

    public DarkPlayer(String playerName) {
        this.playerName = playerName;
    }

    public Integer getLevel() {
        if (Main.isMySQL)
            return MySQL.getLevel(playerName);
        else return Main.getInstance().getDataFile(playerName).getLevel();
    }

    public void setLevel(Integer newLevel) {
        if (Main.isMySQL)
            MySQL.setLevel(playerName, newLevel);
        else Main.getInstance().getDataFile(playerName).setLevel(newLevel);
    }

    public void giveLevel(Integer amount) {
        if (Main.isMySQL)
            MySQL.giveLevel(playerName, amount);
        else Main.getInstance().getDataFile(playerName).giveLevel(amount);
    }

    public void takeLevel(Integer amount) {
        if (Main.isMySQL)
            MySQL.takeLevel(playerName, amount);
        else Main.getInstance().getDataFile(playerName).takeLevel(amount);
    }

    public Double getBalance() {
        if (Main.isMySQL) {
            Main.getInstance().getLogger().fine("MYSQL GET BALANCE "+ playerName + "- > "+MySQL.getBalance(playerName));
            return MySQL.getBalance(playerName);
        }
        else {
            Main.getInstance().getLogger().fine("FILE GET BALANCE "+ playerName + "- > "+Main.getInstance().getDataFile(playerName).getBalance());
            return Main.getInstance().getDataFile(playerName).getBalance();}
    }

    public void setBalance(Double newBalance) {
        if (Main.isMySQL)
            MySQL.setBalance(playerName, newBalance);
        else Main.getInstance().getDataFile(playerName).setBalance(newBalance);
    }

    public void giveBalance(Double amount) {
        if (Main.isMySQL)
            MySQL.giveBalance(playerName, amount);
        else Main.getInstance().getDataFile(playerName).giveBalance(amount);
    }

    public void takeBalance(Double amount) {
        if (Main.isMySQL)
            MySQL.takeBalance(playerName, amount);
        else Main.getInstance().getDataFile(playerName).takeBalance(amount);
    }

    public Integer getPlayedMinutes() {
       /* if (Main.isMySQL)
            return 1; // setDataBase
        else */
        return Main.getInstance().getDataFile(playerName).getPlayedMinutes();
    }

    public void setPlayedMinutes(Integer newPlayedMinutes) {
       /* if (Main.isMySQL)
            ;// setDataBase
        else */
        Main.getInstance().getDataFile(playerName).setPlayedMinutes(newPlayedMinutes);
    }

    public void givePlayedMinutes(Integer amount) {
       /* if (Main.isMySQL)
            ;// setDataBase
        else */
        Main.getInstance().getDataFile(playerName).givePlayedMinutes(amount);
    }

    public void takePlayedMinutes(Integer amount) {
        /*if (Main.isMySQL)
            ;// setDataBase
        else */
        Main.getInstance().getDataFile(playerName).takePlayedMinutes(amount);
    }

    public Integer getPlayedHours() {
        return (int) Math.floor(getPlayedMinutes() / 60);
    }

    public Player getBukkitPlayer() {
        return Bukkit.getPlayer(playerName);
    }
}
