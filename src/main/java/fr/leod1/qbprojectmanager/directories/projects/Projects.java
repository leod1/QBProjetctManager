package fr.leod1.qbprojectmanager.directories.projects;

import fr.leod1.qbprojectmanager.directories.FilesHandler;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Projects implements FilesHandler{

    private String name;
    private String nameHead;
    private int Itemviews;

    private Short MetaData;
    private Material viewItem;
    private String[] viewDesc;

    public Projects(String name, String nameHead, int itemviews, Short metaData) {
        this.name = name;
        this.nameHead = nameHead;
        Itemviews = itemviews;
        MetaData = metaData;
    }

    @Override
    public String getDir() {
        return null;
    }

    @Override
    public void remove() {

    }

    @Override
    public boolean isDir() {
        return false;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public ItemStack getView() {
        final ItemStack item = new ItemStack(this.viewItem, 1,MetaData);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(this.viewDesc));
        item.setItemMeta(meta);
        return item;
    }
}
