package ua.darkphantom1337.coinsapi;

import com.yapzhenyie.GadgetsMenu.GadgetsMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import ru.ZentoFX.uwskywars.SkyWarsCore;
import ru.ZentoFX.uwskywars.game.GamePlayer;
import ua.darkphantom1337.coinsapi.commands.*;
import ua.darkphantom1337.coinsapi.entitys.DarkPlayer;
import ua.darkphantom1337.coinsapi.entitys.Level;
import ua.darkphantom1337.coinsapi.files.ConfigFile;
import ua.darkphantom1337.coinsapi.files.DonateGUIFile;
import ua.darkphantom1337.coinsapi.files.PlayerDataFile;
import ua.darkphantom1337.coinsapi.listeners.BedWarsListener;
import ua.darkphantom1337.coinsapi.listeners.SkyWarsListener;
import ua.darkphantom1337.coinsapi.placeholders.CoinsPlaceholder;
import ua.darkphantom1337.coinsapi.placeholders.LevelPlaceholder;

import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin {

    public ConfigFile cfg;

    private HashMap<String, PlayerDataFile> playerDataFiles;

    public static Boolean isMySQL;
    public static Main coinsAPIPlugin;
    public static Integer maxLevel;

    public DonateGUIFile donateGUIFile;
    public HashMap<Integer, String> donateItemForSlot = new HashMap<Integer, String>();
    public HashMap<Integer, ItemStack> donateGUIItems = new HashMap<Integer, ItemStack>();
    public HashMap<Integer, ItemStack> donateGUIItemsBuy = new HashMap<Integer, ItemStack>();
    public DonateGUI donateGUI;

    public void onEnable() {
        try {
            coinsAPIPlugin = this;
            cfg = new ConfigFile(this, "config.yml");
            donateGUIFile = new DonateGUIFile(this);
            isMySQL = cfg.getB("Settings.UsageMySQL");
            maxLevel = cfg.getInt("Settings.MaxLevel");
            playerDataFiles = new HashMap<String, PlayerDataFile>();
            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                new CoinsPlaceholder(this).register();
                new LevelPlaceholder(this).register();
            } else
                getLogger().info("Placeholder API not found!");
            if (Bukkit.getPluginManager().getPlugin("UWSkyWars") != null)
                Bukkit.getPluginManager().registerEvents(new SkyWarsListener(), this);
            if (Bukkit.getPluginManager().getPlugin("LostBedWars") != null)
                Bukkit.getPluginManager().registerEvents(new BedWarsListener(), this);
            if (Bukkit.getPluginManager().getPlugin("GadgetsMenu") != null)
                GadgetsMenu.setGEconomyProvider(new GEconomy_CoinsAPI(this, "DarkCoinsAPI"));
            loadDonateGUI();
            donateGUI = new DonateGUI(this);
            getCommand("money").setExecutor(new MoneyCMD(this));
            getCommand("shop").setExecutor(new ShopCMD(this));
            getCommand("cspawn").setExecutor(new CSpawnCMD(this));
            getCommand("fly").setExecutor(new FlyCMD(this));
            getCommand("level").setExecutor(new LevelCMD(this));
            getCommand("playedTime").setExecutor(new PlayedTimeCMD(this));
            if (isMySQL)
                MySQL.connectToBase(cfg.getS("MySQL.URL"), cfg.getS("MySQL.DBNAME"), cfg.getS("MySQL.USER"), cfg.getS("MySQL.PASS"));
            MySQL_Donate.connectToBase(cfg.getS("MySQL.URL"), cfg.getS("MySQL.DBNAME"), cfg.getS("MySQL.USER"), cfg.getS("MySQL.PASS"));
            startTimeUpdater();
            startLevelUpdater();
            Bukkit.getPluginManager().registerEvents(new Listeners(), this);
            new ExpireHandler().run();
            new CoinsAPI(this);
            Bukkit.getConsoleSender()
                    .sendMessage("[§eCoinsAPI§a] §f-> §aPlugin successfully enabled! // by DarkPhantom1337, 2021");
        } catch (Exception e) {
            Bukkit.getConsoleSender()
                    .sendMessage("§c[§eCoinsAPI§c] §f-> §cError in enabling plugin! Plugin disabled!\nError:"
                            + e.getLocalizedMessage());
            this.setEnabled(false);
        }
    }

    public void loadDonateGUI() {
        donateGUIItems.clear();
        donateItemForSlot.clear();
        for (String itemName : donateGUIFile.getMainItemsName()) {
            if (donateGUIFile.isEnabledItem(itemName)) {
                donateGUIItems.put(donateGUIFile.getDonateGUIItemSlot(itemName), donateGUIFile.getDonateGUIItem(itemName,false));
                donateGUIItemsBuy.put(donateGUIFile.getDonateGUIItemSlot(itemName), donateGUIFile.getDonateGUIItem(itemName,true));
                donateItemForSlot.put(donateGUIFile.getDonateGUIItemSlot(itemName), itemName);
            }
        }
        for (String itemName : donateGUIFile.getOtherItemsName())
            if (donateGUIFile.isEnabledItem(itemName))
                donateGUIItems.put(donateGUIFile.getDonateGUIItemSlot(itemName), donateGUIFile.getDonateGUIItem(itemName,false));
    }

    public void onDisable() {
        Bukkit.getConsoleSender()
                .sendMessage("§c[§eCoinsAPI§c] §f-> §cPlugin successfully disabled! // by DarkPhantom1337, 2021");
    }

    public PlayerDataFile getDataFile(String playername) {
        if (playerDataFiles.containsKey(playername)) {
            return playerDataFiles.get(playername);
        } else {
            playerDataFiles.remove(playername);
            playerDataFiles.put(playername, new PlayerDataFile(this, playername));
            return getDataFile(playername);
        }
    }

    public static Main getInstance() {
        return coinsAPIPlugin;
    }

    public String getFormattedBalance(Double balance) {
       /* DecimalFormat percentFormatter = new DecimalFormat("###.##", DecimalFormatSymbols.getInstance(Locale.getDefault()));
        return percentFormatter.format(balance);*/
        return String.format("%.2f", balance);
    }

    public GamePlayer getSkyWarsGamePlayer(UUID uuid) {
        return SkyWarsCore.getPC().getPlayer(uuid);
    }

    private void startTimeUpdater() {
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        try {
                            DarkPlayer darkPlayer = new DarkPlayer(p.getName());
                            darkPlayer.givePlayedMinutes(1);
                        } catch (Exception e) {

                        }
                    }
                } catch (Exception e) {

                }
            }
        }.runTaskTimerAsynchronously(this, 20, 20 * 60);
    }

    private void startLevelUpdater() {
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        try {
                            DarkPlayer darkPlayer = new DarkPlayer(p.getName());
                            Integer level = darkPlayer.getLevel();
                            if (level < maxLevel) {
                                Level nextLevel = new Level(darkPlayer.getLevel() + 1);
                                if (darkPlayer.getPlayedHours() >= nextLevel.getNeedHours()) {
                                    darkPlayer.giveLevel(1);
                                    darkPlayer.giveBalance(nextLevel.getUpReward());
                                    darkPlayer.getBukkitPlayer().setLevel(darkPlayer.getLevel());
                                    if (nextLevel.isSendMsgForUp())
                                        darkPlayer.getBukkitPlayer().sendMessage(nextLevel.getUpMessage());
                                }
                            }
                        } catch (Exception e) {

                        }
                    }
                } catch (Exception e) {

                }
            }
        }.runTaskTimer(this, 20, (20 * 60) * 60);
    }


}
