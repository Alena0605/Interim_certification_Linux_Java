package View;

import Controller.Lottery;
import Models.ToyWarehouse;
import Models.Toy;

import java.util.List;
import java.util.Scanner;

public class View {
    ToyWarehouse toyWarehouse = new ToyWarehouse();
    Lottery lottery = new Lottery();

    public void run() {
        createToys();

        while (true) {
            System.out.println("========================================================");
            System.out.println("CHOOSE THE ACTION BELOW:");
            System.out.println("========================================================");

            System.out.println("""
                \t1. Show all toys
                \t2. Hold a lottery
                \t3. Give a toy
                \t0. EXIT
                """);

            try {
                int command = prompt();
                if (command == 0) return;
                switch (command) {
                    case 1 -> getAllToys();
                    case 2 -> startLottery();
                    case 3 -> getToy();
                }
            } catch (Exception ex) {
                System.out.println("========================================================");
                System.out.println("ERROR! Wrong input! Try again.");
                System.out.println("========================================================");
                System.out.println();
            }
        }
    }

    private void createToys() {
        Toy cat = new Toy(1, "Cat", 6, 80);
        Toy dog = new Toy(2, "Dog", 12, 37);
        Toy horse = new Toy(3, "Horse", 8, 10);
        Toy bear = new Toy(4, "Bear", 20, 78);
        Toy tiger = new Toy(5, "Tiger", 15, 49);

        toyWarehouse.addToy(cat);
        toyWarehouse.addToy(dog);
        toyWarehouse.addToy(horse);
        toyWarehouse.addToy(bear);
        toyWarehouse.addToy(tiger);
    }

    private void getAllToys() {
        System.out.println("========================================================");
        System.out.println("YOUR TOYS FOR LOTTERY:");
        System.out.println("========================================================");

        for (Toy toy : toyWarehouse.getToys()) {
            System.out.println(toy);
        }

        System.out.println();
    }

    private void startLottery() {
        List<Toy> winners_list = lottery.getWinnersList(toyWarehouse.getToys());

        System.out.println("========================================================");
        System.out.println("WINNERS LIST:");
        System.out.println("========================================================");

        for (Toy toy : winners_list) {
            System.out.println(toy);
        }

        System.out.println();
    }

    private void getToy() {
        lottery.getToy();
        System.out.println();
    }

    private int prompt() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the command: ");
        return in.nextInt();
    }
}
