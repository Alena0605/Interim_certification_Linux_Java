package Models;

public class Toy {
    private int id;
    private String name;
    private int quantity;
    private int frequency;

    public Toy(String name, int quantity, int frequency) {
        this.name = name;
        this.quantity = quantity;
        this.frequency = frequency;
    }

    public Toy(int id, String name, int quantity, int frequency) {
        this(name, quantity, frequency);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Quantity: %d, Frequency in percent: %d",
                id, name, quantity, frequency);
    }
}
