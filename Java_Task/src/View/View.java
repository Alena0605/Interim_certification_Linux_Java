package View;

import Controller.Lottery;
import Models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {
    private final String allToys = "Toys_warehouse.csv";
    private final String winnersList = "Winners_list.csv";
    private final String winners = "Winners.csv";

    private final FileOperations allToysFile = new FileOperations(allToys);
    private final ToyWarehouse toyWarehouse = new ToyWarehouse(allToysFile);

    private final Lottery lottery = new Lottery(allToys, winnersList, winners);

    public void run() {
        while (true) {
            System.out.println("========================================================");
            System.out.println("CHOOSE THE ACTION BELOW:");
            System.out.println("========================================================");

            System.out.println("""
                \t1. Show all available toys in stock
                \t2. Add a new type of toy to warehouse
                \t3. Hold a lottery
                \t4. Give a toy
                \t5. Change frequency of giving toy
                \t0. EXIT
                """);

            int command = checkNumber("Enter the command: ");
            if (command == 0) return;
            switch (command) {
                case 1 -> getToysInStock();
                case 2 -> addNewToy();
                case 3 -> startLottery();
                case 4 -> getToy();
                case 5 -> changeFrequency();
            }
        }
    }

    private void getToysInStock() {
        List<Toy> toysInWarehouse = toyWarehouse.getToys();
        List<Toy> inStock = new ArrayList<>();

        if (toysInWarehouse.size() != 0) {
            for (Toy toy : toysInWarehouse) {
                if (toy.getQuantity() != 0) {
                    inStock.add(toy);
                }
            }

            if (inStock.size() != 0) {
                System.out.println("========================================================");
                System.out.println("YOUR TOYS FOR LOTTERY:");
                System.out.println("========================================================");

                for (Toy toy : inStock) {
                    System.out.println(toy);
                }

                System.out.println();
                return;
            }
        }

        System.out.println("========================================================");
        System.out.println("YOUR WAREHOUSE IS EMPTY!");
        System.out.println("========================================================");
        System.out.println();
    }

    private void addNewToy() {
        String name = checkName();

        if (name == null) {
            return;
        }

        int quantity;

        while (true) {
            quantity = checkNumber("Enter the quantity: ");

            if ((quantity > 100) || (quantity < 0)) {
                System.out.println("========================================================");
                System.out.println("You can't add less than 1 or more than 100 toys!");
                System.out.println("========================================================");
                System.out.println();
            } else {
                break;
            }
        }

        int frequency = checkFrequency("Indicate frequency of giving toy in percentage: ");
        System.out.println();

        toyWarehouse.addNewToy(new Toy(name, quantity, frequency));
    }

    private void startLottery() {
        List<Toy> toysInWarehouse = toyWarehouse.getToys();

        if (toysInWarehouse.size() != 0) {
            List<Toy> winnersList = lottery.getWinnersList();

            System.out.println("========================================================");
            System.out.println("WINNERS LIST:");
            System.out.println("========================================================");

            for (Toy toy : winnersList) {
                System.out.println(toy);
            }

            System.out.println();
        } else {
            System.out.println("========================================================");
            System.out.println("You can't hold the lottery! Your warehouse is empty!");
            System.out.println("========================================================");
            System.out.println();
        }
    }

    private void getToy() {
        lottery.getToy();
        System.out.println();
    }

    private void changeFrequency() {
        List<Toy> toysInWarehouse = toyWarehouse.getToys();

        if (toysInWarehouse.size() != 0) {
            Toy toy = checkToy(toysInWarehouse);
            System.out.println(toy);
            System.out.println();

            int frequency = checkFrequency("Enter new frequency of giving toy in percentage: ");
            toy.setFrequency(frequency);
            System.out.println();

            lottery.saveChangesOfFrequency(toysInWarehouse, toy);
        } else {
            System.out.println("========================================================");
            System.out.println("YOUR WAREHOUSE IS EMPTY!");
            System.out.println("========================================================");
            System.out.println();
        }
    }

    private String checkName() {
        while (true) {
            System.out.println();
            String name = promptString("Enter the name of the toy (enter 'q' to refuse): ");
            if (name.equals("Q") || name.equals("q")) {
                System.out.println("========================================================");
                System.out.println("You interrupted creating the toy!");
                System.out.println("========================================================");
                System.out.println();
                return null;
            } else if (name.isEmpty()) {
                System.out.println("========================================================");
                System.out.println("ERROR! The field can't be empty!");
                System.out.println("========================================================");
            } else {
                return name;
            }
        }
    }

    private int checkNumber(String message) {
        while (true) {
            try {
                return prompt(message);
            } catch (Exception ex) {
                System.out.println("========================================================");
                System.out.println("ERROR! Wrong input! Try again.");
                System.out.println("========================================================");
                System.out.println();
            }
        }
    }

    private int checkFrequency(String message) {
        while (true) {
            int frequency = checkNumber(message);

            if ((frequency > 100) || (frequency < 0)) {
                System.out.println("========================================================");
                System.out.println("Wrong input! Enter the number from 1 to 100.");
                System.out.println("========================================================");
                System.out.println();
            } else {
                return frequency;
            }
        }
    }

    private Toy checkToy(List<Toy> toysList) {
        System.out.println();
        while (true) {
            int id = checkNumber("Enter the ID of the toy: ");
            for (Toy toy : toysList) {
                if (toy.getId() == id) {
                    return toy;
                }
            }

            System.out.println("========================================================");
            System.out.println("Toy not found! Try again.");
            System.out.println("========================================================");
        }
    }

    private int prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextInt();
    }

    public String promptString(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
