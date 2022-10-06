package fr.leod1.qbprojectmanager.directories;

import org.bukkit.inventory.ItemStack;

public interface FilesHandler {

    String getDir();

    void remove();

    boolean isDir();

    String getName();

    ItemStack getView();
}
