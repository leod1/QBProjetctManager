package fr.leod1.qbprojectmanager.directories;

import fr.leod1.qbprojectmanager.directories.directorie.Directories;
import fr.leod1.qbprojectmanager.directories.projects.Projects;
import fr.leod1.qbprojectmanager.ultils.stockage.DirectoriesserializationGson;
import fr.leod1.qbprojectmanager.ultils.stockage.FileUtils;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

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


    public void runFirst(){
        this.firstdir = new Directories(true,"QBPM",null,null,null);
    }

    public FilesHandler getFile(String path){
        Directories tmp = this.firstdir;
        String[] names;
        names = path.split("/");
        if (names[0].equals("QBPM")){
            boolean v = false;
            for (String i: names){
                tmp = (Directories) tmp.getNextByName(i);
                if (names[0].equals("QBPM") && v == false) {
                    tmp = this.firstdir;
                    v = true;
                }
                if (tmp ==null){
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

    public void addProject(String name, String path){
        FilesHandler target = getFile(path);
        if (target != null){
            if (target instanceof Directories){
                //((Directories) target).getContenante().add(new Projects(name,null,14, (short) 1,name));
            }
        }
    }
}
