package Models;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileOperations {
    private final ToyMapping mapper = new ToyMapping();
    private final String filename;

    public FileOperations(String filename) {
        this.filename = filename;

        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.flush();
        } catch (IOException ex) {
            System.out.println("ERROR! " + ex.getMessage());
        }
    }

    public List<String> readAllLines() {
        List<String> lines = new ArrayList<>();

        try {
            File file = new File(filename);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String line = reader.readLine();
            if (line != null) {
                lines.add(line);
            }

            while (line != null) {
                line = reader.readLine();
                if (line != null) {
                    lines.add(line);
                }
            }
            fr.close();
        } catch (IOException ex) {
            System.out.println("ERROR! " + ex.getMessage());
        }

        return lines;
    }

    public void rewriterFile(List<Toy> toys) {
        try (FileWriter writer = new FileWriter(filename, false)) {
            for (Toy toy : toys) {
                String line = mapper.mapToString(toy);
                writer.write(line);
                writer.append("\n");
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println("ERROR! " + ex.getMessage());
        }
    }

    public void saveGivenToys(Toy toy) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            String currentDate = String.format("%s", new Date());
            writer.write(String.format("%s;%d;%s", currentDate, toy.getId(), toy.getName()));
            writer.append("\n");
            writer.flush();
        } catch (IOException ex) {
            System.out.println("ERROR! " + ex.getMessage());
        }
    }
}
