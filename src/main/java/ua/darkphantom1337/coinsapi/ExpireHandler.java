package ua.darkphantom1337.coinsapi;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.node.types.PermissionNode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExpireHandler extends Thread {

    public void run() {
        this.setName("ExpireController");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy  HH:mm:ss - EEE", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance(), now = Calendar.getInstance();
        calendar.setTime(new Date());
        now.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        Date startDate = calendar.getTime();
        if (!now.before(calendar)) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            startDate = calendar.getTime();
        }
        Main.getInstance().getLogger().info("Congratulations will be launched:  " + dateFormat.format(startDate));
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    Main.getInstance().getLogger().info("Starting ExpireControl...");
                    String nowDate = new SimpleDateFormat("dd.MM.yyyy  HH:mm:ss").format(new Date());
                    for (String username : MySQL_Donate.getIsBuyRankUsers()) {
                        String expireDate = MySQL_Donate.getRankEndDate(username);
                        Main.getInstance().getLogger().info("[ExpireControl] -> [RANK] UserID: " + username + " ExpireDate: " + expireDate);
                        Date eDate = new SimpleDateFormat("dd.MM.yyyy  HH:mm:ss").parse(expireDate);
                        if (eDate.before(new Date())) {
                            Main.getInstance().getLogger().info("[ExpireControl] -> [RANK] UserID: " + username + " ExpireDate: " + expireDate + " Status: EXPIRED");
                            MySQL_Donate.setRank(username, "DarkNotSet");
                            MySQL_Donate.setIsBuyRank(username, false);
                            continue;
                        }
                        int days = daysBetween(new Date(), eDate);
                        Main.getInstance().getLogger().info("[ExpireControl] -> [RANK] UserID: " + username + " ExpireDate: " + expireDate + " Status: " + days + " DAYS BEFORE EXPIRATION");
                    }
                    for (String username : MySQL_Donate.getIsBuyKeepUsers()) {
                        String expireDate = MySQL_Donate.getKeepEndDate(username);
                        Main.getInstance().getLogger().info("[ExpireControl] -> [KEEP] UserID: " + username + " ExpireDate: " + expireDate);
                        Date eDate = new SimpleDateFormat("dd.MM.yyyy  HH:mm:ss").parse(expireDate);
                        if (eDate.before(new Date())) {
                            Main.getInstance().getLogger().info("[ExpireControl] -> [KEEP] UserID: " + username + " ExpireDate: " + expireDate + " Status: EXPIRED");
                            MySQL_Donate.setKeepInventory(username, false);
                            MySQL_Donate.setIsBuyKeep(username, false);
                            Main.getInstance().getLogger().info(LuckPermsProvider.get().getUserManager().getUser(username).data()
                                    .add(PermissionNode.builder("coinsdonate.keep").value(false).build()).wasSuccessful() + "");
                            continue;
                        }
                        int days = daysBetween(new Date(), eDate);
                        Main.getInstance().getLogger().info("[ExpireControl] -> [KEEP] UserID: " + username + " ExpireDate: " + expireDate + " Status: " + days + " DAYS BEFORE EXPIRATION");
                    }
                    for (String username : MySQL_Donate.getIsBuyFlyUsers()) {
                        String expireDate = MySQL_Donate.getFlyEndDate(username);
                        Main.getInstance().getLogger().info("[ExpireControl] -> [FLY] UserID: " + username + " ExpireDate: " + expireDate);
                        Date eDate = new SimpleDateFormat("dd.MM.yyyy  HH:mm:ss").parse(expireDate);
                        if (eDate.before(new Date())) {
                            Main.getInstance().getLogger().info("[ExpireControl] -> [FLY] UserID: " + username + " ExpireDate: " + expireDate + " Status: EXPIRED");
                            MySQL_Donate.setFly(username, false);
                            MySQL_Donate.setIsBuyFly(username, false);
                            Main.getInstance().getLogger().info(LuckPermsProvider.get().getUserManager().getUser(username).data()
                                    .add(PermissionNode.builder("coinsdonate.fly").value(false).build()).wasSuccessful() + "");
                            continue;
                        }
                        int days = daysBetween(new Date(), eDate);
                        Main.getInstance().getLogger().info("[ExpireControl] -> [FLY] UserID: " + username + " ExpireDate: " + expireDate + " Status: " + days + " DAYS BEFORE EXPIRATION");
                    }
                    Main.getInstance().getLogger().info("ExpireControl end. ");
                } catch (Exception ignored) {
                    Main.getInstance().getLogger().fine("Error in ExpireController THREAD");
                    ignored.printStackTrace();
                }
            }
        }, startDate, 86400000L);
    }

    public static int daysBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
}