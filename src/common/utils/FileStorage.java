package common.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileStorage<T> {
    public static final String OUTPUT_DIR = System.getProperty("user.dir") + "\\src\\storage";

    public List<T> listAll(StorageType type) {
        ObjectInputStream inputStream = null;
        List<T> objects = new ArrayList<>();
        try {
            Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
            inputStream = new ObjectInputStream(Files.newInputStream(path));
            objects = (ArrayList<T>) inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                }
            }
        }
        return objects;
    }

    public void save(StorageType type, T object) {
        ObjectOutputStream outputStream = null;
        try {
            Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
            outputStream = new ObjectOutputStream(Files.newOutputStream(path));
            List<T> objects = listAll(StorageType.USER);
            if (objects == null)
                objects = new ArrayList<>();
            objects.add(object);
            outputStream.writeObject(objects);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e) {
                }
            }
        }
    }


    public enum StorageType {
        BOOK, MEMBER, USER;
    }
}
