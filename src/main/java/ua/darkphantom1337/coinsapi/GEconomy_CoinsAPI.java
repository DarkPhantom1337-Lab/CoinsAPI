package ua.darkphantom1337.coinsapi;

import com.yapzhenyie.GadgetsMenu.GadgetsMenu;
import com.yapzhenyie.GadgetsMenu.economy.GEconomyProvider;
import com.yapzhenyie.GadgetsMenu.player.OfflinePlayerManager;
import com.yapzhenyie.GadgetsMenu.utils.PurchaseData;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.node.types.PermissionNode;
import org.bukkit.plugin.Plugin;
import ua.darkphantom1337.coinsapi.entitys.DarkPlayer;

public class GEconomy_CoinsAPI extends GEconomyProvider {

    public GEconomy_CoinsAPI(Plugin plugin, String storage) {
        super(plugin, storage);
    }

    @Override
    public int getMysteryDust(OfflinePlayerManager offlinePlayerManager) {
        return new DarkPlayer(offlinePlayerManager.getName()).getBalance().intValue();
    }

    @Override
    public boolean addMysteryDust(OfflinePlayerManager offlinePlayerManager, int i) {
        try {
            new DarkPlayer(offlinePlayerManager.getName()).giveBalance((double) i);
            return true;
        } catch (Exception e) {
            return true;
        }
    }

    @Override
    public boolean setMysteryDust(OfflinePlayerManager offlinePlayerManager, int i) {
        try {
            new DarkPlayer(offlinePlayerManager.getName()).setBalance((double) i);
            return true;
        } catch (Exception e) {
            return true;
        }
    }

    @Override
    public boolean removeMysteryDust(OfflinePlayerManager offlinePlayerManager, int i) {
        try {
            new DarkPlayer(offlinePlayerManager.getName()).takeBalance((double) i);
            PurchaseData purchaseData = GadgetsMenu.getPlayerManager(offlinePlayerManager.getPlayer().getPlayer()).purchaseData();
            LuckPermsProvider.get().getUserManager().getUser(offlinePlayerManager.getUUID()).data()
                    .add(PermissionNode.builder(purchaseData.getPermission()).value(true).build());
            LuckPermsProvider.get().getUserManager().saveUser(LuckPermsProvider.get().getUserManager().getUser(offlinePlayerManager.getUUID()));
            return true;
        } catch (Exception e) {
            return true;
        }
    }
}
