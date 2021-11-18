package ua.darkphantom1337.coinsapi;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.node.types.PermissionNode;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import ua.darkphantom1337.coinsapi.entitys.DarkPlayer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Listeners implements Listener {

    @EventHandler
    public void onInvClick0(InventoryClickEvent e) {
        try {
            if (e.getClickedInventory() != null && e.getClickedInventory().getType().equals(InventoryType.CHEST)) {
                String inventoryName = e.getView().getTitle();
                Player whoClicked = (Player) e.getWhoClicked();
                Integer clickedSlot = e.getSlot();
                if (inventoryName.equals(Main.getInstance().donateGUIFile.getString("DonateGUI.Name"))) {
                    e.setCancelled(true);
                    if (e.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY
                            || e.getAction() == InventoryAction.COLLECT_TO_CURSOR)
                        e.setCancelled(true);
                    e.setResult(Event.Result.DENY);
                    if (Main.getInstance().donateItemForSlot.containsKey(clickedSlot)) {
                        String clickedItem = Main.getInstance().donateItemForSlot.get(clickedSlot);
                        if (clickedItem.contains("rank")) {
                            if (MySQL_Donate.getRank(whoClicked.getName()).equals("DarkNotSet")) {
                                if (new DarkPlayer(whoClicked.getName()).getBalance() >= new Rank(clickedItem).getRankPrice()) {
                                    new DarkPlayer(whoClicked.getName()).takeBalance(new Rank(clickedItem).getRankPrice().doubleValue());
                                    MySQL_Donate.setRank(whoClicked.getName(), clickedItem);
                                    LuckPermsProvider.get().getUserManager().getUser(e.getWhoClicked().getUniqueId()).data()
                                            .add(PermissionNode.builder("coinsdonate." + clickedItem).value(true).build());
                                    LuckPermsProvider.get().getUserManager().saveUser(LuckPermsProvider.get().getUserManager().getUser(e.getWhoClicked().getUniqueId()));
                                    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy  HH:mm:ss");
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.setTime(new Date());
                                    calendar.set(Calendar.HOUR_OF_DAY, 9);
                                    calendar.set(Calendar.MINUTE, 0);
                                    calendar.set(Calendar.SECOND, 0);
                                    Date endDate = calendar.getTime();
                                    calendar.add(Calendar.DAY_OF_MONTH, 30);
                                    endDate = calendar.getTime();
                                    MySQL_Donate.setIsBuyRank(whoClicked.getName(), true);
                                    MySQL_Donate.setRankEndDate(whoClicked.getName(), dateFormat.format(endDate));
                                    whoClicked.closeInventory();
                                    whoClicked.sendMessage("§aВы успешно купили ранк! Действует до: " + dateFormat.format(endDate));
                                    return;
                                } else {
                                    whoClicked.sendMessage("§cУ вас не хватает денег для покупки данного ранка.");
                                    return;
                                }
                            } else {
                                whoClicked.sendMessage("§cУ вас уже есть приобретённый ранк.");
                                return;
                            }
                        } else {
                            if (clickedItem.equals("keep")) {
                                if (MySQL_Donate.getKeepInventory(whoClicked.getName()) == false) {
                                    if (new DarkPlayer(whoClicked.getName()).getBalance() >= new Rank(clickedItem).getRankPrice()) {
                                        new DarkPlayer(whoClicked.getName()).takeBalance(new Rank(clickedItem).getRankPrice().doubleValue());
                                        LuckPermsProvider.get().getUserManager().getUser(e.getWhoClicked().getUniqueId()).data()
                                                .add(PermissionNode.builder("coinsdonate.keep").value(true).build());
                                        LuckPermsProvider.get().getUserManager().saveUser(LuckPermsProvider.get().getUserManager().getUser(e.getWhoClicked().getUniqueId()));
                                        MySQL_Donate.setKeepInventory(whoClicked.getName(), true);
                                        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy  HH:mm:ss");
                                        Calendar calendar = Calendar.getInstance();
                                        calendar.setTime(new Date());
                                        calendar.set(Calendar.HOUR_OF_DAY, 9);
                                        calendar.set(Calendar.MINUTE, 0);
                                        calendar.set(Calendar.SECOND, 0);
                                        Date endDate = calendar.getTime();
                                        calendar.add(Calendar.DAY_OF_MONTH, 30);
                                        endDate = calendar.getTime();
                                        MySQL_Donate.setIsBuyKeep(whoClicked.getName(), true);
                                        MySQL_Donate.setKeepEndDate(whoClicked.getName(), dateFormat.format(endDate));
                                        whoClicked.closeInventory();
                                        whoClicked.sendMessage("§aВы успешно купили услугу 'Сохранение инвентаря'! Действует до: " + dateFormat.format(endDate));
                                        return;
                                    } else {
                                        whoClicked.sendMessage("§cУ вас не хватает денег для покупки данной услуги.");
                                        return;
                                    }
                                } else {
                                    whoClicked.sendMessage("§cУ вас уже есть приобретённая услуга 'Сохранение инвентаря'.");
                                    return;
                                }
                            }
                            if (clickedItem.equals("fly")) {
                                if (MySQL_Donate.getFly(whoClicked.getName()) == false) {
                                    if (new DarkPlayer(whoClicked.getName()).getBalance() >= new Rank(clickedItem).getRankPrice()) {
                                        new DarkPlayer(whoClicked.getName()).takeBalance(new Rank(clickedItem).getRankPrice().doubleValue());
                                        MySQL_Donate.setKeepInventory(whoClicked.getName(), true);
                                        LuckPermsProvider.get().getUserManager().getUser(e.getWhoClicked().getUniqueId()).data()
                                                .add(PermissionNode.builder("coinsdonate.fly").value(true).build());
                                        LuckPermsProvider.get().getUserManager().saveUser(LuckPermsProvider.get().getUserManager().getUser(e.getWhoClicked().getUniqueId()));
                                        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy  HH:mm:ss");
                                        Calendar calendar = Calendar.getInstance();
                                        calendar.setTime(new Date());
                                        calendar.set(Calendar.HOUR_OF_DAY, 9);
                                        calendar.set(Calendar.MINUTE, 0);
                                        calendar.set(Calendar.SECOND, 0);
                                        Date endDate = calendar.getTime();
                                        calendar.add(Calendar.DAY_OF_MONTH, 30);
                                        endDate = calendar.getTime();
                                        MySQL_Donate.setIsBuyFly(whoClicked.getName(), true);
                                        MySQL_Donate.setKeepEndDate(whoClicked.getName(), dateFormat.format(endDate));
                                        whoClicked.closeInventory();
                                        whoClicked.sendMessage("§aВы успешно купили услугу 'Полёт'! Действует до: " + dateFormat.format(endDate));
                                        return;
                                    } else {
                                        whoClicked.sendMessage("§cУ вас не хватает денег для покупки данной услуги.");
                                        return;
                                    }
                                } else {
                                    whoClicked.sendMessage("§cУ вас уже есть приобретённая услуга 'Полёт'.");
                                    return;
                                }
                            }
                            if (clickedItem.equals("akill")) {
                                if (MySQL_Donate.getAnimationForKill(whoClicked.getName()) == false) {
                                    if (new DarkPlayer(whoClicked.getName()).getBalance() >= new Rank(clickedItem).getRankPrice()) {
                                        new DarkPlayer(whoClicked.getName()).takeBalance(new Rank(clickedItem).getRankPrice().doubleValue());
                                        MySQL_Donate.setAnimationForKill(whoClicked.getName(), true);
                                        LuckPermsProvider.get().getUserManager().getUser(e.getWhoClicked().getUniqueId()).data()
                                                .add(PermissionNode.builder("coinsdonate.akill").value(true).build());
                                        LuckPermsProvider.get().getUserManager().saveUser(LuckPermsProvider.get().getUserManager().getUser(e.getWhoClicked().getUniqueId()));
                                        whoClicked.closeInventory();
                                        whoClicked.sendMessage("§aВы успешно купили услугу 'Анимация при убийстве'! Действует пожизненно.");
                                        return;
                                    } else {
                                        whoClicked.sendMessage("§cУ вас не хватает денег для покупки данной услуги.");
                                        return;
                                    }
                                } else {
                                    whoClicked.sendMessage("§cУ вас уже есть приобретённая услуга 'Анимация при убийстве'.");
                                    return;
                                }
                            }
                        }
                    }
                    return;
                }
            }
        } catch (Exception e1) {

        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        String name = e.getEntity().getName();
        if (MySQL_Donate.getIsBuyKeep(name))
            e.setKeepInventory(true);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        if (Main.getInstance().cfg.getCfgFile().isSet("SpawnLocation")) {
            e.setRespawnLocation(Main.getInstance().cfg.getCfgFile().getLocation("SpawnLocation"));
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        String name = e.getPlayer().getName();
        if (Main.getInstance().cfg.getCfgFile().isSet("SpawnLocation")) {
            e.getPlayer().teleport(Main.getInstance().cfg.getCfgFile().getLocation("SpawnLocation"));
        }
        if (MySQL_Donate.getIsBuyRank(name)) {
            Bukkit.broadcastMessage(new Rank(MySQL_Donate.getRank(name)).getRankMsg(name));
            String rank = MySQL_Donate.getRank(name);
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (rank.contains("rank_warlock"))
                        e.getPlayer().spawnParticle(Particle.SPELL_INSTANT, e.getPlayer().getLocation(), 250, 0, -5, 0, 0.05);
                    if (rank.contains("rank_necromancer"))
                        e.getPlayer().spawnParticle(Particle.EXPLOSION_HUGE, e.getPlayer().getLocation(), 250, 0, -5, 0, 0.05);
                    if (rank.contains("rank_witch"))
                        e.getPlayer().spawnParticle(Particle.FLAME, e.getPlayer().getLocation(), 250, 0, -5, 0, 0.05);
                }
            }.runTaskLater(Main.getInstance(), 10);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent e) {
        String name = e.getPlayer().getName();
        if (MySQL_Donate.getIsBuyRank(name))
            e.setMessage(new Rank(MySQL_Donate.getRank(name)).getRankColor() + e.getMessage());
    }


}
