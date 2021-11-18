package ua.darkphantom1337.coinsapi.files;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import ua.darkphantom1337.coinsapi.DarkYAMLFileAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DonateGUIFile extends DarkYAMLFileAPI {

    public DonateGUIFile(Plugin plugin) {
        super(plugin, "donateGUISettings", plugin.getDataFolder());
    }

    @Override
    public void firstFill() {
        writeDefaultFirstLine();
        setString("DonateGUI.Name", "   §0Покупка плюшек");
        setInt("DonateGUI.Size", 9);
        addBlancLine();
        setList("OtherItemsName", new ArrayList<String>());
        addBlancLine();
        /*int slot = 0;
        for (String itemID : getMainItemsName()) {
            setString("DonateGUI.Items." + itemID + ".Name", "§y" + itemID);
            setList("DonateGUI.Items." + itemID + ".Lore", Arrays.asList(
                    "§7Line number one",
                    "§7Line number two",
                    "§7Line number three ",
                    "§7Item: " + itemID,
                    "§fby DarkPhantom1337"));
            setString("DonateGUI.Items." + itemID + ".Material", "IRON_BLOCK");
            setInt("DonateGUI.Items." + itemID + ".Data", 0);
            setInt("DonateGUI.Items." + itemID + ".Slot", slot);
            setBoolean("DonateGUI.Items." + itemID + ".Enabled", true);
            slot++;
        }*/
        setString("DonateGUI.Items." + "rank_warlock" + ".Name", "§aРанг Чернокнижник");
        setList("DonateGUI.Items." + "rank_warlock" + ".Lore", Arrays.asList(
                "§7Зелёный цвет в чате," ,
                        "§7анимация при заходе на сервер," ,
                        "§7оповещение в чате" ,
                        "§7'" ,
                        "§fЦена: 100 монеток" ,
                        "§fСрок: 30 дней"));
        setString("DonateGUI.Items." + "rank_warlock" + ".Material", "IRON_BLOCK");
        setInt("DonateGUI.Items." + "rank_warlock" + ".Data", 0);
        setInt("DonateGUI.Items." + "rank_warlock" + ".Slot", 0);
        setBoolean("DonateGUI.Items." + "rank_warlock" + ".Enabled", true);
        addBlancLine();
        setString("DonateGUI.Items." + "rank_necromancer" + ".Name", "§1Ранг Некромант");
        setList("DonateGUI.Items." + "rank_necromancer" + ".Lore", Arrays.asList(
                "§7Синий цвет в чате," ,
                        "§7анимация при заходе на сервер," ,
                        "§7оповещение в чате" ,
                        "§7'" ,
                        "§fЦена: 200 монеток" ,
                        "§fСрок: 30 дней"));
        setString("DonateGUI.Items." + "rank_necromancer" + ".Material", "IRON_BLOCK");
        setInt("DonateGUI.Items." + "rank_necromancer" + ".Data", 0);
        setInt("DonateGUI.Items." + "rank_necromancer" + ".Slot", 1);
        setBoolean("DonateGUI.Items." + "rank_necromancer" + ".Enabled", true);
        addBlancLine();
        setString("DonateGUI.Items." + "rank_witch" + ".Name", "§5eРанг Колдун");
        setList("DonateGUI.Items." + "rank_witch" + ".Lore", Arrays.asList(
                "§7Жёлтый цвет в чате," ,
                        "§7анимация при заходе на сервер," ,
                        "§7оповещение в чате" ,
                        "§7'" ,
                        "§fЦена: 300 монеток" ,
                        "§fСрок: 30 дней"));
        setString("DonateGUI.Items." + "rank_witch" + ".Material", "IRON_BLOCK");
        setInt("DonateGUI.Items." + "rank_witch" + ".Data", 0);
        setInt("DonateGUI.Items." + "rank_witch" + ".Slot", 2);
        setBoolean("DonateGUI.Items." + "rank_witch" + ".Enabled", true);
        addBlancLine();
        setString("DonateGUI.Items." + "keep" + ".Name", "§eУслуга KeepInventory");
        setList("DonateGUI.Items." + "keep" + ".Lore", Arrays.asList(
                "'§7сохрнаняет инвентарь" ,
                        "§7после вашей смерти." ,
                        "§7 :-)" ,
                        "§7" ,
                        "§fЦена: 400 монеток" ,
                        "§fСрок: 30 дней"));
        setString("DonateGUI.Items." + "keep" + ".Material", "IRON_BLOCK");
        setInt("DonateGUI.Items." + "keep" + ".Data", 0);
        setInt("DonateGUI.Items." + "keep" + ".Slot", 3);
        setBoolean("DonateGUI.Items." + "keep" + ".Enabled", true);
        addBlancLine();
        setString("DonateGUI.Items." + "fly" + ".Name", "§yУслуга FLY");
        setList("DonateGUI.Items." + "fly" + ".Lore", Arrays.asList(
                "'§7сДаёт возможность летать" ,
                        "§7" ,
                        "§fЦена: 500 монеток" ,
                        "§fСрок: 30 дней"));
        setString("DonateGUI.Items." + "fly" + ".Material", "IRON_BLOCK");
        setInt("DonateGUI.Items." + "fly" + ".Data", 0);
        setInt("DonateGUI.Items." + "fly" + ".Slot", 4);
        setBoolean("DonateGUI.Items." + "fly" + ".Enabled", true);
        addBlancLine();
        setString("DonateGUI.Items." + "akill" + ".Name", "§yАнимация при убийстве");
        setList("DonateGUI.Items." + "akill" + ".Lore", Arrays.asList(
                "'§7сДаёт анимацию при убийстве" ,
                        "§7при убийстве на BedWars" ,"§7",
                        "§fЦена: 30 монеток" ,
                        "§fСрок: навсегда"));
        setString("DonateGUI.Items." + "akill" + ".Material", "IRON_BLOCK");
        setInt("DonateGUI.Items." + "akill" + ".Data", 0);
        setInt("DonateGUI.Items." + "akill" + ".Slot", 5);
        setBoolean("DonateGUI.Items." + "akill" + ".Enabled", true);
        saveFileConfiguration();
        System.out.println("[DarkYAMLFileAPI] -> File: " + getFileName() + " ID: " + getFileID() + " successfully filled!");
    }

    public ItemStack getDonateGUIItem(String itemName, Boolean isBuy) {
        ItemStack item = new ItemStack(Material.valueOf(getString("DonateGUI.Items." + itemName + ".Material")));
        if (isBuy)
            item.setType(Material.EMERALD_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(getString("DonateGUI.Items." + itemName + ".Name"));
        meta.setLore(getStringList("DonateGUI.Items." + itemName + ".Lore"));
        if (getFileConfiguration().isSet("DonateGUI.Items." + itemName + ".Data"))
            item.setDurability(getInt("DonateGUI.Items." + itemName + ".Data").shortValue());
        item.setItemMeta(meta);
        return item;
    }

    public Boolean isEnabledItem(String itemName) {
        return getBoolean("DonateGUI.Items." + itemName + ".Enabled");
    }

    public Integer getDonateGUIItemSlot(String itemName) {
        return getInt("DonateGUI.Items." + itemName + ".Slot");
    }

    public List<String> getOtherItemsName() {
        return getStringList("OtherItemsName");
    }

    public List<String> getMainItemsName() {
        return Arrays.asList("rank_warlock", "rank_necromancer", "rank_witch"
                , "keep", "fly", "akill");
    }

}
