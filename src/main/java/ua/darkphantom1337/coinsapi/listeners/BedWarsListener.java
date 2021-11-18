package ua.darkphantom1337.coinsapi.listeners;

import me.losteddev.bedwars.api.event.game.BedWarsGameEndEvent;
import me.losteddev.bedwars.api.event.player.BedWarsPlayerDeathEvent;
import me.losteddev.bedwars.api.server.BedWarsTeam;
import org.bukkit.Particle;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import ua.darkphantom1337.coinsapi.Main;
import ua.darkphantom1337.coinsapi.MySQL_Donate;
import ua.darkphantom1337.coinsapi.entitys.DarkPlayer;

import java.util.List;

public class BedWarsListener implements Listener {

    @EventHandler
    public void onPlayerDeathInBedWarsGame(BedWarsPlayerDeathEvent e) {
        if (e.getPlayer() != null && e.getKiller() != null) {
            Player victim = e.getPlayer(), killer = e.getKiller();
            DarkPlayer darkPlayer = new DarkPlayer(killer.getName());
            Double reward = Main.getInstance().cfg.getReward("ForBedWarsKill");
            if (e.getServer().getAlive() == e.getServer().getTeam(e.getKiller()).getSize()) {
                if (MySQL_Donate.getAnimationForKill(killer.getName())) {
                    final Entity ent = e.getPlayer().getLocation().getWorld().spawn(e.getPlayer().getLocation(), Blaze.class);
                    e.getPlayer().getLocation().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, e.getPlayer().getLocation(), 500);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            ent.remove();
                        }
                    }.runTaskLater(Main.getInstance(), 20 * 5);
                    System.out.println(" FINALLY KILL by " + e.getKiller().getName());
                }
            }
            darkPlayer.giveBalance(Main.getInstance().cfg.getReward("ForBedWarsKill"));
            if (Main.getInstance().cfg.getSettings("SendMessageForBedWarsKill"))
                killer.sendMessage(Main.getInstance().cfg.getMessage("BedWarsKill")
                        .replace("%victim%", victim.getName())
                        .replace("%reward%", Main.getInstance().getFormattedBalance(reward)));
            return;
        }
    }

    @EventHandler
    public void onBedWarsEndGame(BedWarsGameEndEvent e) {
        BedWarsTeam winnerTeam = e.getWinnerTeam();
        for (Player winner : (List<Player>) winnerTeam.getMembers()) {
            if (winner != null) {
                DarkPlayer darkPlayer = new DarkPlayer(winner.getName());
                Double reward = Main.getInstance().cfg.getReward("ForBedWarsWin");
                darkPlayer.giveBalance(Main.getInstance().cfg.getReward("ForBedWarsWin"));
                if (Main.getInstance().cfg.getSettings("SendMessageForBedWarsWin"))
                    winner.sendMessage(Main.getInstance().cfg.getMessage("BedWarsWin")
                            .replace("%reward%", Main.getInstance().getFormattedBalance(reward)));
                continue;
            }
        }
    }
}
