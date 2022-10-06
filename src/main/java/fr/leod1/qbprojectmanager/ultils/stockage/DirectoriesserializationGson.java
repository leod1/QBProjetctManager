package fr.leod1.qbprojectmanager.ultils.stockage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.leod1.qbprojectmanager.directories.FilesHandler;
import fr.leod1.qbprojectmanager.directories.directorie.Directories;

public class DirectoriesserializationGson {
    private Gson gson = createRightGson();

    private Gson createRightGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(FilesHandler.class, new InterfaceAdapter());
        Gson gson = builder.create();
        return (gson);
        //return (new GsonBuilder()).setPrettyPrinting().serializeNulls().disableHtmlEscaping().create();
    }

    public String serializeDirectories(Directories projet) {
        return this.gson.toJson(projet);
    }

    public Directories deserializeDirectories(String json) {
        return (Directories)this.gson.fromJson(json, Directories.class);
    }

    public String serializePlayerData(Directories dataPlayer) {
        return this.gson.toJson(dataPlayer);
    }

    public Directories deserializePlayerData(String json) {
        return (Directories)this.gson.fromJson(json, Directories.class);
    }
}
