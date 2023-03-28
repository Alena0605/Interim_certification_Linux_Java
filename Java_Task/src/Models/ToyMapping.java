package Models;

public class ToyMapping {
    public String mapToString(Toy toy) {
        return String.format("%d;%s;%d;%d", toy.getId(), toy.getName(), toy.getQuantity(), toy.getFrequency());
    }

    public Toy mapToToy(String line) {
        String[] lines = line.split(";");
        return new Toy(Integer.parseInt(lines[0]), lines[1], Integer.parseInt(lines[2]), Integer.parseInt(lines[3]));
    }
}
