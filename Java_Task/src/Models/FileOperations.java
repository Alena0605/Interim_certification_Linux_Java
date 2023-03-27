package Models;

import java.io.FileWriter;
import java.io.IOException;

public class FileOperations {
    private final String filename;

    public FileOperations(String filename) {
        this.filename = filename;

        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void saveGivenToys(Toy toy) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(String.format("ID: %d, Name: %s", toy.getId(), toy.getName()));
            writer.append("\n");
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
