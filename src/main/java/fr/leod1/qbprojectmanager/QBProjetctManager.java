package fr.leod1.qbprojectmanager;

import fr.leod1.qbprojectmanager.GUIInterface.PlayerinvManager;
import fr.leod1.qbprojectmanager.GUIInterface.QBDataPlayer;
import fr.leod1.qbprojectmanager.cmd.Qbl;
import fr.leod1.qbprojectmanager.events.ChatEvent;
import fr.leod1.qbprojectmanager.events.JoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import fr.leod1.qbprojectmanager.cmd.Test;
import fr.leod1.qbprojectmanager.directories.FilesManager;

import java.util.HashMap;

public final class QBProjetctManager extends JavaPlugin {

    public static QBProjetctManager MAINQB;
    public FilesManager filesmanager;
    public HashMap<Player, PlayerinvManager> linkedPlayerInventory;
    public HashMap<Player, QBDataPlayer> linkedPlayerData;

    @Override
    public void onEnable() {
        MAINQB = this;
        filesmanager = new FilesManager();
        linkedPlayerInventory = new HashMap<>();
        linkedPlayerData = new HashMap<>();
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new ChatEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerinvManager(), this);
        this.getCommand("qbl").setExecutor(new Qbl());
        for (Player pl: Bukkit.getOnlinePlayers()) {
            linkedPlayerInventory.put(pl,new PlayerinvManager(pl));
            linkedPlayerData.put(pl,new QBDataPlayer(null,null, null));
        }
    }

    @Override
    public void onDisable() {
        filesmanager.onStop();
    }

}
