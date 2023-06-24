package Gamestore;


import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;

public class Storage<E extends Toy> implements Serializable, Iterable<E> {
    private List<E> toyStorage;

    public Storage() {
        this.toyStorage = new ArrayList<>();
    }

    public Storage(List<E> toyStorage) {
        this.toyStorage = toyStorage;
    }

    public List<E> getToyStorage() {
        return toyStorage;
    }

    public void add(E toy) {
        if (!toyStorage.contains(toy)) {
            toyStorage.add(toy);
        }
    }

    public void addPrize(E toy) {
        toyStorage.add(toy);
    }

    public void remove(E toy) {
        if (toyStorage.contains(toy)) {
            toyStorage.remove(toy);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ToyItterator<>(toyStorage);
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Iterable.super.forEach(action);
    }
}
