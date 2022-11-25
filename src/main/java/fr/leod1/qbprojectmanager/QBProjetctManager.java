package fr.leod1.qbprojectmanager;

import fr.leod1.qbprojectmanager.GUIInterface.PlayerinvManager;
import fr.leod1.qbprojectmanager.GUIInterface.QBDataPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
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
        //System.out.println("zebi 2");
        MAINQB = this;
        filesmanager = new FilesManager();
        linkedPlayerInventory = new HashMap<>();
        getServer().getPluginManager().registerEvents(new PlayerinvManager(), this);
        this.getCommand("test").setExecutor(new Test());
        for (Player pl: Bukkit.getOnlinePlayers()) {
            linkedPlayerInventory.put(pl,new PlayerinvManager(pl));
            linkedPlayerData.put(pl,new QBDataPlayer(null,null));
        }
    }

    @Override
    public void onDisable() {
        filesmanager.onStop();
    }

}
