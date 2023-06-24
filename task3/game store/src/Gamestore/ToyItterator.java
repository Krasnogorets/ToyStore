package Gamestore;

import java.util.Iterator;
import java.util.List;

class ToyItterator<E extends Toy> implements Iterator<E> {

    private int index;
    private List<E> toyStorage;

    public ToyItterator(List<E> toyStorage) {
        this.toyStorage = toyStorage;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < toyStorage.size();
    }

    @Override
    public E next() {
        return toyStorage.get(index++);
    }
}


