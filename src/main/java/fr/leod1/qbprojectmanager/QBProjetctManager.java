package fr.leod1.qbprojectmanager;

import fr.leod1.qbprojectmanager.GUIInterface.PlayerinvManager;
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

    @Override
    public void onEnable() {
        MAINQB = this;
        filesmanager = new FilesManager();
        linkedPlayerInventory = new HashMap<>();
        getServer().getPluginManager().registerEvents(new PlayerinvManager(), this);
        this.getCommand("test").setExecutor(new Test());
        for (Player pl: Bukkit.getOnlinePlayers()) {
            linkedPlayerInventory.put(pl,new PlayerinvManager(pl));
        }
    }

    @Override
    public void onDisable() {
        filesmanager.onStop();
    }

}
