package prosseses;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SingletonFile {
    private static SingletonFile instance;
    private SingletonFile() {}

    public static synchronized SingletonFile getInstance() {
        if (instance == null) {
            instance = new SingletonFile();
        }
        return instance;
    }

    public String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    public void writeFile(String filePath, String content) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
        }
    }
}
