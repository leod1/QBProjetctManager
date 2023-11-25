package fr.leod1.qbprojectmanager.GUIInterface;

import org.bukkit.inventory.ItemStack;

public class QBDataPlayer {
    String Path;
    String event;

    ItemStack itemOn;

    public ItemStack getItemOn() {
        return itemOn;
    }

    public QBDataPlayer(String path, String event, ItemStack itemOn) {
        Path = path;
        this.event = event;
        this.itemOn = itemOn;
    }

    public void setItemOn(ItemStack itemOn) {
        this.itemOn = itemOn;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void Delete(){
        this.event = null;
        this.Path = null;
    }
}
