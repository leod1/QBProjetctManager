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
    }

    public ArrayList<FilesHandler> getContenante() {
        return contenante;
    }

    @Override
    public String getDir() {
        return null;
    }

    @Override
    public void remove(String name) {
        if (this.root == false){
            /*System.out.println(path + " gt");
            ArrayList<String> pathUl = new ArrayList<String>();
            for (String i:path.split("/")) {
                pathUl.add(i);
            }
            pathUl.remove(pathUl.size()-1);
            String pathU = "";
            for (String i: pathUl){
                pathU = pathU + i;
            }

            System.out.println(pathU + " gt");
            Directories Upper = (Directories) MAINQB.filesmanager.getFile(pathU);*/
            System.out.println(path);
            System.out.println(debugContenantes() + "      YT");
            for (FilesHandler i: this.getContenante()) {
                System.out.println(name + " gt" + i.getName());
                if (i.getName().equals(name)){
                    this.getContenante().remove(i);
                }
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
        ItemStack item = new ItemStack(this.viewItem, 1, MetaData);
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
