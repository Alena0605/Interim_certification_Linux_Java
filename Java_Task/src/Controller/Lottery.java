package Controller;

import Models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lottery {
    private final FileOperations allToysFile;
    private final FileOperations winnersListFile;
    private final FileOperations saveWinnerFile;

    private final ToyWarehouse toyWarehouse;
    private final ToyWarehouse getWinners;

    public Lottery(String allToys, String  winnersList, String saveWinner) {
        this.allToysFile = new FileOperations(allToys);
        this.winnersListFile = new FileOperations(winnersList);
        this.saveWinnerFile = new FileOperations(saveWinner);

        this.toyWarehouse = new ToyWarehouse(allToysFile);
        this.getWinners = new ToyWarehouse(winnersListFile);
    }

    public List<Toy> getWinnersList() {
        List<Toy> allToysList = toyWarehouse.getToys();
        List<Toy> winnersToysList = getWinners.getToys();

        if (winnersToysList.size() == 0) {
            List<Integer> toysQuantity = new ArrayList<>();

            for (Toy toy : allToysList) {
                int q = toy.getQuantity();
                toysQuantity.add(q);
            }

            int count = 0;

            while (count < 5) {
                Random rand = new Random();
                int toyID = rand.nextInt(allToysList.size());
                int quan = toysQuantity.get(toyID);

                if (quan > 0) {
                    winnersToysList.add(allToysList.get(toyID));
                    toysQuantity.set(toyID, quan - 1);
                    count += 1;
                }
            }

            winnersListFile.rewriterFile(winnersToysList);

        } else {
            System.out.println("========================================================");
            System.out.println("You can't run a new lottery while you have toys for giving!");
            System.out.println();
        }

        return winnersToysList;
    }

    public void getToy() {
        List<Toy> allToysList = toyWarehouse.getToys();
        List<Toy> winnersToysList = getWinners.getToys();

        if (winnersToysList.size() != 0) {
            Toy toy = winnersToysList.get(0);
            winnersToysList.remove(0);

            for (Toy winner : winnersToysList) {
                if (winner.getId() == toy.getId()) {
                    winner.setQuantity(winner.getQuantity() - 1);
                }
            }

            int toyID = toy.getId();
            Toy findToy = allToysList.get(toyID - 1);
            findToy.setQuantity(findToy.getQuantity() - 1);

            allToysFile.rewriterFile(allToysList);
            winnersListFile.rewriterFile(winnersToysList);
            saveWinnerFile.saveGivenToys(toy);

            System.out.println("========================================================");
            System.out.printf("The following toy has been given: %s\n", toy.getName());
            System.out.println("========================================================");
        } else {
            System.out.println("========================================================");
            System.out.println("All toys were given!");
            System.out.println("========================================================");
        }
    }

    public void saveChangesOfFrequency(List<Toy> toysList, Toy toy) {
        List<Toy> winnersToysList = getWinners.getToys();

        if (winnersToysList.size() != 0) {
            for (Toy winner : winnersToysList) {
                if (toy.getId() == winner.getId()) {
                    winner.setFrequency(toy.getFrequency());
                }
            }

            winnersListFile.rewriterFile(winnersToysList);
        }

        allToysFile.rewriterFile(toysList);
    }
}
