package ua.darkphantom1337.coinsapi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class DonateGUI {

    private Main donatePlugin;

    public DonateGUI(Main donatePlugin) {
        this.donatePlugin = donatePlugin;
    }

    public void openDonateGUI(Player requested) {
        try {
            Inventory donateGUI = Bukkit.createInventory(requested,
                    donatePlugin.donateGUIFile.getInt("DonateGUI.Size"),
                    donatePlugin.donateGUIFile.getString("DonateGUI.Name"));
            for (Integer slot : donatePlugin.donateGUIItems.keySet()) {
                String item = donatePlugin.donateItemForSlot.get(slot);
                if (item.equals("keep"))
                    if (MySQL_Donate.getIsBuyKeep(requested.getName()))
                        donateGUI.setItem(slot, donatePlugin.donateGUIItemsBuy.get(slot));
                    else donateGUI.setItem(slot, donatePlugin.donateGUIItems.get(slot));
                if (item.equals("fly"))
                    if (MySQL_Donate.getIsBuyFly(requested.getName()))
                        donateGUI.setItem(slot, donatePlugin.donateGUIItemsBuy.get(slot));
                    else donateGUI.setItem(slot, donatePlugin.donateGUIItems.get(slot));
                if (item.contains("rank"))
                    if (MySQL_Donate.getIsBuyRank(requested.getName()) && MySQL_Donate.getRank(requested.getName()).equals(item))
                        donateGUI.setItem(slot, donatePlugin.donateGUIItemsBuy.get(slot));
                    else donateGUI.setItem(slot, donatePlugin.donateGUIItems.get(slot));
                if (item.contains("akill"))
                    if (MySQL_Donate.getAnimationForKill(requested.getName()))
                        donateGUI.setItem(slot, donatePlugin.donateGUIItemsBuy.get(slot));
                    else donateGUI.setItem(slot, donatePlugin.donateGUIItems.get(slot));
            }
            requested.openInventory(donateGUI);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
