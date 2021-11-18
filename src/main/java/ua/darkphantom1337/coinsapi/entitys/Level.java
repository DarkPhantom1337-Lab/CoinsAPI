package ua.darkphantom1337.coinsapi.entitys;


import ua.darkphantom1337.coinsapi.Main;

public class Level {

    private Integer level;
    private Main plugin;

    public Level(Integer lvl) {
        this.level = lvl;
        this.plugin = Main.getInstance();
    }

    /*	public int getNeedCoins() {
            return plugin.cfg.getInt("Levels." + level + ".NeedCoins");
        }
        Need it or not?
    */

    public int getNeedHours() {
        return plugin.cfg.getInt("Levels." + level + ".NeedHours");
    }

    public Double getUpReward() {
        return plugin.cfg.getCfgFile().getDouble("Levels." + level + ".Reward");
    }

    public boolean isSendMsgForUp() {
        return plugin.cfg.getB("Levels." + level + ".SendMsg");
    }



    public String getUpMessage() {
        return plugin.cfg.getMessage("UpLevel").replace("%level%", level.toString())
                .replace("%reward%", getUpReward().toString());
    }

}
