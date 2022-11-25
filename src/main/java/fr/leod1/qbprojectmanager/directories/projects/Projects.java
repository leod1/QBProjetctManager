package fr.leod1.qbprojectmanager.directories.projects;

import fr.leod1.qbprojectmanager.directories.FilesHandler;
import fr.leod1.qbprojectmanager.ultils.Qbloc;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Projects implements FilesHandler{

    private String name;
    private String nameHead;
    //private int Itemviews;

    private Short MetaData;
    private Material viewItem;
    private String[] viewDesc;

    private Qbloc loc;

    public Projects(String name, String nameHead, Material viewItem, Short metaData, Qbloc loc) {
        this.name = name;
        this.nameHead = nameHead;
        this.viewItem = viewItem;
        this.MetaData = metaData;
        this.loc = loc;
    }

    @Override
    public String getDir() {
        return null;
    }

    @Override
    public void remove(String name) {

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
        //meta.setLore(Arrays.asList(this.viewDesc));
        item.setItemMeta(meta);
        return item;
    }
    public Location getLoc() {
        return this.loc.getLocation();
    }
}
