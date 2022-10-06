package fr.leod1.qbprojectmanager.ultils.stockage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
    public static boolean createFile(File file) throws IOException {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return true;
        }
        return false;
    }

    public static void save(File file, String text) {
        try {
            createFile(file);
            FileWriter fw = new FileWriter(file);
            fw.write(text);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String loadContent(File file) throws IOException {
        if (file.exists())
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                StringBuilder text = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null)
                    text.append(line);
                reader.close();
                return text.toString();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        return "";
    }
}
