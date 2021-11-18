package ua.darkphantom1337.coinsapi.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import ru.ZentoFX.uwskywars.game.GameEndEvent;
import ua.darkphantom1337.coinsapi.Main;
import ua.darkphantom1337.coinsapi.entitys.DarkPlayer;

public class SkyWarsListener implements Listener {

    @EventHandler
    public void onPlayerDeathInSkyWarsGame(PlayerDeathEvent e) {
        if (e.getEntity() != null && e.getEntity().getKiller() != null) {
            Player victim = e.getEntity(), killer = victim.getKiller();
            if (Main.getInstance().getSkyWarsGamePlayer(killer.getUniqueId()).inGame()) {
                DarkPlayer darkPlayer = new DarkPlayer(killer.getName());
                Double reward = Main.getInstance().cfg.getReward("ForSkyWarsKill");
                darkPlayer.giveBalance(Main.getInstance().cfg.getReward("ForSkyWarsKill"));
                if (Main.getInstance().cfg.getSettings("SendMessageForSkyWarsKill"))
                    killer.sendMessage(Main.getInstance().cfg.getMessage("SkyWarsKill")
                            .replace("%victim%", victim.getName())
                            .replace("%reward%", Main.getInstance().getFormattedBalance(reward)));
                return;
            }
        }
    }

    @EventHandler
    public void onSkyWarsEndGame(GameEndEvent e) {
        Player winner = e.getWinner();
        if (winner != null) {
            DarkPlayer darkPlayer = new DarkPlayer(winner.getName());
            Double reward = Main.getInstance().cfg.getReward("ForSkyWarsWin");
            darkPlayer.giveBalance(Main.getInstance().cfg.getReward("ForSkyWarsWin"));
            if (Main.getInstance().cfg.getSettings("SendMessageForSkyWarsWin"))
                winner.sendMessage(Main.getInstance().cfg.getMessage("SkyWarsWin")
                        .replace("%reward%", Main.getInstance().getFormattedBalance(reward)));
            return;
        }
    }
}
