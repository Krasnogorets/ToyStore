package Gamestore;

import java.io.Serializable;
import java.util.Objects;

public class Toy implements Serializable, Comparable<Toy> {
    private int toyID;
    private String toyName;
    private int quantity;
    private int frequency;


    public Toy(int toyID, String toyName, int quantity, int frequency) {
        this.toyID = toyID;
        this.toyName = toyName;
        this.quantity = quantity;
        this.frequency = frequency;
    }

    public int getToyID() {
        return toyID;
    }

    public String getToyName() {
        return toyName;
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

    public void setToyName(String toyName) {
        this.toyName = toyName;
    }


    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getInfo() {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(getToyID());
        sb.append(" |");
        sb.append(getToyName());
        i = sb.length();
        if (i < 40) {
            for (int t = 0; t <= (40 - i); t++) {
                sb.append(" ");
            }
        }
        sb.append(" |");
        sb.append(getQuantity());
        sb.append(" шт.");
        sb.append(" |");
        sb.append(getFrequency());
        sb.append(" %");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Toy toy = (Toy) o;
        return Objects.equals(toyName, toy.toyName);

    }

    @Override
    public int compareTo(Toy o) {
        return this.toyName.compareTo(o.toyName);
    }
}
