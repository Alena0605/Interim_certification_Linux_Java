package Models;

import java.util.ArrayList;
import java.util.List;

public class ToyWarehouse {
    List<Toy> allToys = new ArrayList<>();

    public List<Toy> getToys() {
        return allToys;
    }

    public void addToy(Toy toy) {
        allToys.add(toy);
    }
}
