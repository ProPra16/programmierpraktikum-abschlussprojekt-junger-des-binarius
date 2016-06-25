package system;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by mayma on 25.06.2016.
 */

public class FileIO {

    public static String readFromFile(String filepath){
        String content= "";
        Path path = Paths.get(filepath);
        try{
            content = new String(Files.readAllBytes(path));
        }catch(IOException fileNotFound){
            System.out.println("Kann Datei nicht lesen | Pfad: "+Paths.get(filepath).toAbsolutePath());
        }
        return content;
    }

    public static void writeToFile(String content,String filepath,boolean append){
        File file = new File(filepath);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file,append);
            writer.write(content);
            writer.close();
        }catch(IOException cannotCreate){
            System.out.println("Datei konnte nicht gefunden und erstellt werden | Pfad: "+file.getAbsolutePath());
        }
    }
}
