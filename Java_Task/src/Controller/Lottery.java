package Controller;

import Models.FileOperations;
import Models.Toy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lottery {
    FileOperations fileOperations = new FileOperations("Winners_toys.csv");
    private final List<Toy> winnersToysList = new ArrayList<>();

    public List<Toy> getWinnersList(List<Toy> allToys) {
        if (winnersToysList.size() == 0) {
            List<Integer> toysQuantity = new ArrayList<>();

            for (Toy toy : allToys) {
                int q = toy.getQuantity();
                toysQuantity.add(q);
            }

            int count = 0;

            while (count < 5) {
                Random rand = new Random();
                int toyID = rand.nextInt(allToys.size());
                int quan = toysQuantity.get(toyID);

                if (quan > 0) {
                    winnersToysList.add(allToys.get(toyID));
                    toysQuantity.set(toyID, quan - 1);
                    count += 1;
                }
            }
        } else {
            System.out.println("========================================================");
            System.out.println("You have toys for giving!");
            System.out.println("========================================================");
        }

        return winnersToysList;
    }

    public void getToy() {
        if (winnersToysList.size() != 0) {
            Toy toy = winnersToysList.get(0);
            winnersToysList.remove(0);
            toy.setQuantity(toy.getQuantity() - 1);
            fileOperations.saveGivenToys(toy);

            System.out.println("========================================================");
            System.out.printf("The following toy has been given: %s\n", toy.getName());
            System.out.println("========================================================");
        } else {
            System.out.println("========================================================");
            System.out.println("All toys were given!");
            System.out.println("========================================================");
        }
    }
}
