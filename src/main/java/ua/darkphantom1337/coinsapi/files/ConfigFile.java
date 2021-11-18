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
import java.util.List;

public class ConfigFile {

    private FileConfiguration filecfg;
    private Main plugin;
    private File file;
    private String filename;
    private String pluginname;

    public ConfigFile(Main plugin, String filename) {
        this.plugin = plugin;
        this.filename = filename;
        this.pluginname = plugin.getName();
        setupCfgFile();
        if (getCfgFile().isSet(pluginname))
            saveCfgFile();
        else
            firstFill();
    }

    private void setupCfgFile() {
        if (!plugin.getDataFolder().exists())
            plugin.getDataFolder().mkdir();
        file = new File(plugin.getDataFolder(), filename);
        if (!file.exists())
            try {
                file.createNewFile();
            } catch (IOException localIOException) {
                System.out.println("Error in creating file " + filename + "!");
            }
        filecfg = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getCfgFile() {
        return filecfg;
    }

    public void saveCfgFile() {
        try {
            filecfg.save(file);
        } catch (IOException localIOException) {
            System.out.println("Error in saving file " + filename + "!");
        }
    }

    public void reloadCfgFile() {
        filecfg = YamlConfiguration.loadConfiguration(file);
    }

    private void firstFill() {
        getCfgFile().set(pluginname, " File: " + filename + " || Author: DarkPhantom1337");

        getCfgFile().set("MySQL.URL", "mysql.joinserver.ru:3306");
        getCfgFile().set("MySQL.DBNAME", "s48980_s1");
        getCfgFile().set("MySQL.USER", "u48980_Fah6SSGcJr");
        getCfgFile().set("MySQL.PASS", "ZoDtB3vCF45tnCuclYlYBZgz");

        getCfgFile().set("Settings.UsageMySQL", true);
        getCfgFile().set("Settings.SendMessageForSkyWarsKill", true);
        getCfgFile().set("Settings.SendMessageForSkyWarsWin", true);
        getCfgFile().set("Settings.SendMessageForBedWarsKill", true);
        getCfgFile().set("Settings.SendMessageForBedWarsWin", true);
        getCfgFile().set("Settings.MaxLevel", 5);

        getCfgFile().set("Rewards.ForSkyWarsKill", 1);
        getCfgFile().set("Rewards.ForSkyWarsWin", 3);
        getCfgFile().set("Rewards.ForBedWarsKill", 1);
        getCfgFile().set("Rewards.ForBedWarsWin", 3);

        getCfgFile().set("Ranks.rank_warlock.Name", "[WARLOCK]");
        getCfgFile().set("Ranks.rank_warlock.Color", "§a");
        getCfgFile().set("Ranks.rank_warlock.Join", "§aЧернокнижник %player% зашёл на сервер.");
        getCfgFile().set("Ranks.rank_warlock.Price", 100);
        getCfgFile().set("Ranks.rank_necromancer.Name", "[NECROMANCER]");
        getCfgFile().set("Ranks.rank_necromancer.Color", "§1");
        getCfgFile().set("Ranks.rank_necromancer.Join", "§1Некромант %player% зашёл на сервер.");
        getCfgFile().set("Ranks.rank_necromancer.Price", 200);
        getCfgFile().set("Ranks.rank_witch.Name", "[WITCH]");
        getCfgFile().set("Ranks.rank_witch.Color", "§e");
        getCfgFile().set("Ranks.rank_witch.Join", "§eКолдун %player% зашёл на сервер.");
        getCfgFile().set("Ranks.rank_witch.Price", 300);
        getCfgFile().set("Ranks.keep.Price", 400);
        getCfgFile().set("Ranks.fly.Price", 500);
        getCfgFile().set("Ranks.keep.Enabled", true);
        getCfgFile().set("Ranks.fly.Enabled", true);
        getCfgFile().set("Ranks.akill.Price", 30);
        getCfgFile().set("Ranks.akill.Enabled", true);
        for (int i = 1; i <= 5; i++) {
            getCfgFile().set("Levels." + i + ".NeedHours", i);
            getCfgFile().set("Levels." + i + ".Reward", (double) i);
            getCfgFile().set("Levels." + i + ".SendMsg", true);
        }

        getCfgFile().set("Messages.SeeMyInfo", "&a[CoinsAPI] &f-> Ваш баланс %balance%, Вы на %level% уровне и отыграли %hours% часов на сервере.");
        getCfgFile().set("Messages.SkyWarsKill", "&a[CoinsAPI] &f-> За убийство игрока %victim% Вам начислено %reward% коинов.");
        getCfgFile().set("Messages.SkyWarsWin", "&a[CoinsAPI] &f-> За победу на SkyWars Вам начислено %reward% коинов.");
        getCfgFile().set("Messages.BedWarsKill", "&a[CoinsAPI] &f-> За убийство игрока %victim% Вам начислено %reward% коинов.");
        getCfgFile().set("Messages.BedWarsWin", "&a[CoinsAPI] &f-> За победу на BedWars Вам начислено %reward% коинов.");
        getCfgFile().set("Messages.UpLevel", "&a[CoinsAPI] &f-> Вы достигли уровня %level%. Поздравляем и дарим Вам %reward% коинов.");
        saveCfgFile();
    }

    public void setS(String path, String value) {
        getCfgFile().set(path, value);
        saveCfgFile();
    }

    public List<String> getLS(String path) {
        return getCfgFile().getStringList(path);
    }

    public Integer getInt(String path) {
        return getCfgFile().getInt(path);
    }

    public String getS(String path) {
        return getCfgFile().getString(path).replaceAll("&", "§");
    }

    public Boolean getB(String path) {
        return getCfgFile().getBoolean(path);
    }

    public String getMessage(String messageID) {
        return getS("Messages." + messageID);
    }

    public Double getReward(String rewardID) {
        return getCfgFile().getDouble("Rewards." + rewardID);
    }

    public Boolean getSettings(String settingsID) {
        return getB("Settings." + settingsID);
    }


}
