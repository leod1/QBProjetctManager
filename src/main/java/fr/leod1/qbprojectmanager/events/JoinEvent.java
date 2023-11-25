package fr.leod1.qbprojectmanager.events;

import fr.leod1.qbprojectmanager.GUIInterface.PlayerinvManager;
import fr.leod1.qbprojectmanager.GUIInterface.QBDataPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static fr.leod1.qbprojectmanager.QBProjetctManager.MAINQB;

public class JoinEvent implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        MAINQB.linkedPlayerInventory.put(e.getPlayer(),new PlayerinvManager(e.getPlayer()));
        MAINQB.linkedPlayerData.put(e.getPlayer(),new QBDataPlayer(null,null, null));
    }
}
