package ua.darkphantom1337.coinsapi;

public class Rank {

    private String rankID;

    public Rank(String rankID) {
        this.rankID = rankID;
    }

    public String getRankName() {
        return Main.getInstance().cfg.getS("Ranks." + rankID + ".Name");
    }

    public String getRankColor() {
        return Main.getInstance().cfg.getS("Ranks." + rankID + ".Color");
    }

    public Integer getRankPrice() {
        return Main.getInstance().cfg.getCfgFile().getInt("Ranks." + rankID + ".Price");
    }

    public String getRankMsg(String user) {
        return Main.getInstance().cfg.getCfgFile().getString("Ranks." + rankID + ".Join").replace("%player%", user);
    }
}
