package fr.leod1.qbprojectmanager.directories.directorie;

import fr.leod1.qbprojectmanager.directories.FilesHandler;
import fr.leod1.qbprojectmanager.directories.projects.Projects;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static fr.leod1.qbprojectmanager.QBProjetctManager.MAINQB;

public class Directories implements FilesHandler {
    private boolean root;
    private String name;
    private String path;
    private ArrayList<FilesHandler> contenante;
    private HashMap<Integer, String> views;

    private Short MetaData;
    private Material viewItem;
    private String[] viewDesc;

    public Directories(boolean root, String name, String path, Short metaData, Material viewItem) {
        this.root = root;
        this.name = name;
        this.path = path;
        MetaData = metaData;
        this.viewItem = viewItem;
        contenante = new ArrayList<>();
        views = new HashMap<>();
        //contenante.add(new Projects("Retour",null,))
    }

    public ArrayList<FilesHandler> getContenante() {
        return contenante;
    }

    @Override
    public String getDir() {
        return null;
    }

    @Override
    public void remove() {
        if (this.root == false){
            String pathU = String.join("/", Arrays.copyOf(path.split("/"),path.split("/").length-1));
            System.out.println(path);
            System.out.println(pathU);
            if (MAINQB.filesmanager.getFile(pathU) instanceof Directories){
                Directories a = (Directories) MAINQB.filesmanager.getFile(pathU);
                Directories b = null;
                for (FilesHandler f: a.getContenante()) {
                    System.out.println(f.getName());
                    if (f.getName().equalsIgnoreCase(this.name)){
                        b = (Directories) f;
                    }
                }
                a.getContenante().remove(b);
            }
        }
    }

    @Override
    public boolean isDir() {
        return true;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ItemStack getView() {
        ItemStack item = new ItemStack(this.viewItem, 1,MetaData);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        //meta.setLore(Arrays.asList(this.viewDesc));
        item.setItemMeta(meta);
        return item;
    }

    public FilesHandler getNextByName(String name){
        for (FilesHandler i: contenante){
            if (i.getName().equals(name)){
                return i;
            }
        }
        return null;
    }

    public String debugContenantes(){
        String r = "";
        for (FilesHandler i: contenante){
            r = r + i.getName();
        }
        return r;
    }
    public void deleteContentsByName(String name){
        for (FilesHandler f: this.contenante) {
            if (f.getName().equalsIgnoreCase(name)){
                this.contenante.remove(f);
            }
        }
    }
}
