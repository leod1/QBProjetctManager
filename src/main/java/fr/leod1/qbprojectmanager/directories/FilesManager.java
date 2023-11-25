package fr.leod1.qbprojectmanager.directories;

import fr.leod1.qbprojectmanager.directories.directorie.Directories;
import fr.leod1.qbprojectmanager.directories.projects.Projects;
import fr.leod1.qbprojectmanager.ultils.Qbloc;
import fr.leod1.qbprojectmanager.ultils.stockage.DirectoriesserializationGson;
import fr.leod1.qbprojectmanager.ultils.stockage.FileUtils;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static fr.leod1.qbprojectmanager.QBProjetctManager.MAINQB;

public class FilesManager {

    public Directories firstdir;

    private DirectoriesserializationGson serialization;


    public FilesManager() {
        try {
            onStart();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onStart() throws IOException {
        this.serialization = new DirectoriesserializationGson();
        File DirectoryProj = new File(MAINQB.getDataFolder(), "/projectsqb/");
        if (!DirectoryProj.exists()){
            DirectoryProj.mkdirs();
            runFirst();
        } else {
            firstdir = serialization.deserializeDirectories(FileUtils.loadContent(new File(DirectoryProj, "projects.json")));
        }
    }

    public void onStop(){
        save();
    }

    public void save() {
        this.serialization = new DirectoriesserializationGson();
        File DirectoryProj = new File(MAINQB.getDataFolder(), "/projectsqb/");
        DirectoryProj.mkdirs();
        File file = new File(DirectoryProj, "projects.json");
        String json = this.serialization.serializeDirectories(firstdir);
        FileUtils.save(file, json);
    }

    public void doHardSave(){
        this.serialization = new DirectoriesserializationGson();
        File DirectoryProj = new File(MAINQB.getDataFolder(), "/projectsqbSAVE/");
        DirectoryProj.mkdirs();
        File file = new File(DirectoryProj, "projects.json");
        String json = this.serialization.serializeDirectories(firstdir);
        FileUtils.save(file, json);
    }


    public void runFirst(){
        this.firstdir = new Directories(true,"QBPM",null,null,null);
    }

    public FilesHandler getFile(String path){
        FilesHandler tmp = this.firstdir;
        ArrayList<String> names = new ArrayList<String>();
        for (String i:path.split("/")) {
            names.add(i);
        }
        if (names.get(0).equals("QBPM")){
            boolean v = false;
            for (String i: names){
                if (tmp instanceof Projects){
                    return tmp;
                }
                tmp = ((Directories) tmp).getNextByName(i);
                if (names.get(0).equals("QBPM") && v == false) {
                    tmp = this.firstdir;
                    v = true;
                }
                if (tmp == null){
                    return null;
                }
            }
            return tmp;
        } else {
            return null;
        }
    }

    public void addDirectory(String name, String path, ItemStack onHand){
        FilesHandler target = getFile(path);
        if (target != null){
            if (target instanceof Directories){
                ((Directories) target).getContenante().add(new Directories(false,name,path,onHand.getDurability(),onHand.getType()));
            }
        }
    }

    public void addProject(String name, String path, ItemStack onHand, Qbloc qbloc){
        FilesHandler target = getFile(path);
        if (target != null){
            if (target instanceof Directories){
                ((Directories) target).getContenante().add(new Projects(name,null,onHand.getType(), onHand.getDurability(), qbloc));
            }
        }
    }

    public void remove(String name, String path){
        FilesHandler target = getFile(path);
        if (target != null){
            FilesHandler rm = null;
            for (FilesHandler i: ((Directories) target).getContenante()) {
                if (i.getName().equals(name)){
                    rm = i;
                }
            }
            ((Directories) target).getContenante().remove(rm);
        }
    }

    public boolean nameExist(String name, String path){
        FilesHandler target = getFile(path);
        for (FilesHandler i: ((Directories) target).getContenante()) {
            if (i.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public boolean pathExist(String path){
        if (path.substring(path.length() - 1).equalsIgnoreCase("/")){
            return false;
        }
        ArrayList<String> names = new ArrayList<String>();
        for (String i:path.split("/")) {
            names.add(i);
        }
        int lastIndex = names.size() - 1;
        String x = names.get(lastIndex);
        if (names.size() > 1){
            names.remove(lastIndex);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < names.size(); i++) {
            sb.append(names.get(i));
            if (i < names.size() - 1) {
                sb.append("/");
            }
        }
        Directories target = (Directories) getFile(sb.toString());
        for (FilesHandler file: target.getContenante()) {
            if (file.getName().equals(x)){
                return true;
            }
        }
        return false;
    }
}
