package Models;

import java.util.ArrayList;
import java.util.List;

public class ToyWarehouse {
    private final ToyMapping mapper = new ToyMapping();
    private final FileOperations fileOperations;

    public ToyWarehouse(FileOperations fileOperations) {
        this.fileOperations = fileOperations;
    }

    public List<Toy> getToys() {
        List<String> lines = fileOperations.readAllLines();
        List<Toy> allToys = new ArrayList<>();
        for (String line : lines) {
            allToys.add(mapper.mapToToy(line));
        }
        return allToys;
    }

    public void addNewToy(Toy toy) {
        List<Toy> toys = getToys();
        int maxID = toys.size();

        int newID = maxID + 1;
        toy.setId(newID);
        toys.add(toy);

        fileOperations.rewriterFile(toys);
    }
}
