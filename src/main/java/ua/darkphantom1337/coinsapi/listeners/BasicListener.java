package ua.darkphantom1337.coinsapi.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import ua.darkphantom1337.coinsapi.Main;
import ua.darkphantom1337.coinsapi.entitys.DarkPlayer;

public class BasicListener implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        DarkPlayer player = new DarkPlayer(e.getPlayer().getName());
        if (Main.getInstance().isMySQL) {
            if (player.getLevel() == -1337) {
                player.setBalance(0.0);
                player.setLevel(1);
            }
        }
        e.getPlayer().setLevel(player.getLevel());
    }

    @EventHandler
    public void onExpChange(PlayerExpChangeEvent e){
        e.setAmount(0);
    }
}
