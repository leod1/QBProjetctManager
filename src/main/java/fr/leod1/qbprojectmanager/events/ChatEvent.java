package fr.leod1.qbprojectmanager.events;

import fr.leod1.qbprojectmanager.GUIInterface.QBDataPlayer;
import fr.leod1.qbprojectmanager.ultils.Qbloc;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static fr.leod1.qbprojectmanager.QBProjetctManager.MAINQB;

public class ChatEvent implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        if (MAINQB.linkedPlayerData.get(e.getPlayer()).getEvent() != null) {
            if (MAINQB.linkedPlayerData.get(e.getPlayer()).getEvent().equalsIgnoreCase("nameDir")) {
                QBDataPlayer QBPLData = MAINQB.linkedPlayerData.get(e.getPlayer());
                if (MAINQB.filesmanager.nameExist(e.getMessage(),QBPLData.getPath())){
                    MAINQB.linkedPlayerData.replace(e.getPlayer(), new QBDataPlayer(null, null, null));
                    e.getPlayer().sendMessage("§8[§7QBL§8] §fDésolé mais le nom §7" + e.getMessage()+" §fest déjà pris !");
                    e.setCancelled(true);
                }else {
                    MAINQB.filesmanager.addDirectory(e.getMessage(), QBPLData.getPath(), QBPLData.getItemOn());
                    MAINQB.linkedPlayerData.replace(e.getPlayer(), new QBDataPlayer(null, null, null));
                    e.getPlayer().sendMessage("§8[§7QBL§8] §fLe dossier §7" + e.getMessage()+ " §fa bien été créer !");
                    e.setCancelled(true);
                }

            } else if (MAINQB.linkedPlayerData.get(e.getPlayer()).getEvent().equalsIgnoreCase("nameFil")) {
                QBDataPlayer QBPLData = MAINQB.linkedPlayerData.get(e.getPlayer());
                if (MAINQB.filesmanager.nameExist(e.getMessage(),QBPLData.getPath())){
                    MAINQB.linkedPlayerData.replace(e.getPlayer(), new QBDataPlayer(null, null, null));
                    e.getPlayer().sendMessage("§8[§7QBL§8] §fDésolé mais le nom §7" + e.getMessage()+" §fest déjà pris !");
                    e.setCancelled(true);

                }else {
                    MAINQB.filesmanager.addProject(e.getMessage(), QBPLData.getPath(), QBPLData.getItemOn(), new Qbloc(e.getPlayer().getWorld().getName(), e.getPlayer().getLocation().getX(), e.getPlayer().getLocation().getY(), e.getPlayer().getLocation().getZ(), e.getPlayer().getLocation().getYaw(), e.getPlayer().getLocation().getPitch()));
                    MAINQB.linkedPlayerData.replace(e.getPlayer(), new QBDataPlayer(null, null, null));
                    e.getPlayer().sendMessage("§8[§7QBL§8] §fLe dossier §7" + e.getMessage()+ " §fa bien été créer !");
                    e.setCancelled(true);
                }
            }
        }
    }
}
