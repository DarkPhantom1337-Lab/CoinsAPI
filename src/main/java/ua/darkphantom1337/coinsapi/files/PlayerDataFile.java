/*
 * @Author DarkPhantom1337
 * @Version 1.0.0
 */
package ua.darkphantom1337.coinsapi.files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import ua.darkphantom1337.coinsapi.Main;

import java.io.File;
import java.io.IOException;

public class PlayerDataFile {

    private FileConfiguration filecfg;
    private Main plugin;
    private File file;
    private String filename;
    private String pluginname;

    public PlayerDataFile(Main plugin, String player_name) {
        this.plugin = plugin;
        this.filename = player_name + ".yml";
        this.pluginname = plugin.getName();
        setupPlayerDataFile();
        if (getPlayerDataFile().isSet(pluginname))
            savePlayerDataFile();
        else
            firstFill();
    }

    private void setupPlayerDataFile() {
        if (!plugin.getDataFolder().exists())
            plugin.getDataFolder().mkdir();
        String dirpath = plugin.getDataFolder() + File.separator + "DataFiles";
        if (!new File(dirpath).exists())
            new File(dirpath).mkdir();
        file = new File(dirpath, filename);
        if (!file.exists())
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error in creating data file " + filename + "!");
                e.printStackTrace();
            }
        filecfg = YamlConfiguration.loadConfiguration(file);
    }

    private FileConfiguration getPlayerDataFile() {
        return filecfg;
    }


    public File getFile() {
        return file;
    }

    public void savePlayerDataFile() {
        try {
            filecfg.save(file);
        } catch (IOException localIOException) {
            System.out.println("Error in saving data file " + filename + "!");
        }
    }

    public void reloadPlayerDataFile() {
        filecfg = YamlConfiguration.loadConfiguration(file);
    }

    private void firstFill() {
        getPlayerDataFile().set(pluginname, " File: " + filename + " || Author: DarkPhantom1337");
        getPlayerDataFile().set("Level", 1);
        getPlayerDataFile().set("Balance", 0.0);
        getPlayerDataFile().set("PlayedMinutes", 0);
        savePlayerDataFile();
    }

    public Integer getLevel() {
        return getPlayerDataFile().getInt("Level");
    }

    public void setLevel(Integer new_level) {
        new_level = new_level < 0 ? 1 : new_level;
        getPlayerDataFile().set("Level", new_level);
        savePlayerDataFile();
    }

    public void giveLevel(Integer amount) {
        if (getLevel() + amount <= 0)
            setLevel(1);
        else
            setLevel(getLevel() + amount);
    }

    public void takeLevel(Integer amount) {
        if (getLevel() - amount <= 0)
            setLevel(1);
        else
            setLevel(getLevel() - amount);
    }

    public Double getBalance() {
        return getPlayerDataFile().getDouble("Balance");
    }

    public void setBalance(Double new_Balance) {
        new_Balance = new_Balance < 0 ? 0.0 : new_Balance;
        getPlayerDataFile().set("Balance", new_Balance);
        savePlayerDataFile();
    }

    public void giveBalance(Double amount) {
        if (getBalance() + amount <= 0)
            setBalance(0.0);
        else
            setBalance(getBalance() + amount);
    }

    public void takeBalance(Double amount) {
        if (getBalance() - amount <= 0)
            setBalance(0.0);
        else
            setBalance(getBalance() - amount);
    }

    public Integer getPlayedMinutes() {
        return getPlayerDataFile().getInt("PlayedMinutes");
    }

    public void setPlayedMinutes(Integer new_PlayedMinutes) {
        new_PlayedMinutes = new_PlayedMinutes < 0 ? 0 : new_PlayedMinutes;
        getPlayerDataFile().set("PlayedMinutes", new_PlayedMinutes);
        savePlayerDataFile();
    }

    public void givePlayedMinutes(Integer amount) {
        if (getPlayedMinutes() + amount <= 0)
            setPlayedMinutes(0);
        else
            setPlayedMinutes(getPlayedMinutes() + amount);
    }

    public void takePlayedMinutes(Integer amount) {
        if (getPlayedMinutes() - amount <= 0)
            setPlayedMinutes(0);
        else
            setPlayedMinutes(getPlayedMinutes() - amount);
    }

}
