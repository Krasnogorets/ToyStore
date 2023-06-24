package Gamestore;

import SaveLoad.SaveTxt;
import SaveLoad.Saveable;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Service<E extends Toy> {
    protected Storage<E> toyStorage;
    protected Storage<E> PrizeList;
    protected Storage<E> PrizeOut;
    private Saveable saveable;

    public Service(Storage<E> toyStorage, Storage<E> PrizeList) {
        this.PrizeList = PrizeList;
        this.toyStorage = toyStorage;
        saveable = (Saveable) new SaveTxt();
    }


    public StringBuilder printPrizeList() throws IOException, ClassNotFoundException {
        LoadPrizeStorage();
        System.out.println("id | название |");
        StringBuilder sb = new StringBuilder();
        sb.append("кол-во игрушек готовых к выдаче: ");
        sb.append(PrizeList.getToyStorage().size());
        sb.append("\n");
        for (E Toy : PrizeList) {
            sb.append(Toy.getInfo());
            sb.append(" \n");
        }
        return sb;
    }

    public StringBuilder printStorage() {
        System.out.println("id | название | кол-во   | вес");
        StringBuilder sb = new StringBuilder();
        sb.append("в магазине: ");
        sb.append(toyStorage.getToyStorage().size());
        sb.append(" видов игрушек: \n");
        for (E Toy : toyStorage) {
            sb.append(Toy.getInfo());
            sb.append(" \n");
        }
        return sb;
    }

    public boolean SaveToyStorage() throws IOException {
        if (saveable == null) {
            return false;
        }
        return saveable.save(toyStorage, "ToyStorage.txt");
    }

    public boolean LoadStorage() throws IOException, ClassNotFoundException {
        if (saveable == null) {
            return false;
        }
        toyStorage = (Storage) saveable.load("ToyStorage.txt");
        return true;
    }

    public boolean SavePrizeStorage() throws IOException {
        if (saveable == null) {
            return false;
        }
        return saveable.save(PrizeList, "giftStorage.txt");
    }

    public boolean LoadPrizeStorage() throws IOException, ClassNotFoundException {
        if (saveable == null) {
            return false;
        }
        PrizeList = (Storage) saveable.load("giftStorage.txt");
        return true;
    }


    public String AddToy(int toyID, String toyName, int quantity, int frequency) {
        toyID = GetLastId(toyStorage) + 1;
        Toy toy = new Toy(toyID, toyName, quantity, frequency);
        if (checkToy(toyStorage, toy)) {
            toyStorage.add((E) toy);
            return "Игрушка успешно добавлена на склад";
        } else {
            return "Такая игрушка уже есть на складе";
        }
    }

    public boolean checkToy(Storage<E> toyStorage, Toy toy) {
        for (Toy toySearch : toyStorage) {
            if (toy.equals(toySearch)) {
                return false;
            }

        }
        return true;
    }

    private int GetLastId(Storage<E> list) {
        int lastId = 0;
        int id = 0;
        for (E toy : list) {
            id = toy.getToyID();
            if (id > lastId) {
                lastId = id;
            }
        }
        return lastId;
    }

    public String ChangeToyInfo(int toyID, int frequency) {
        for (E toy : toyStorage) {
            int id = toy.getToyID();
            if (id == toyID) {
                toy.setFrequency(frequency);
                return "Вес игрушки изменен";
            }
        }
        return "игрушки с таким id нет на складе";

    }

    public String ToyLottery() throws IOException {
        System.out.println("разыгрываем -------------------------------------------");
        int[] id = new int[toyStorage.getToyStorage().size()];
        int[] chance = new int[toyStorage.getToyStorage().size()];
        int[] probability = new int[toyStorage.getToyStorage().size()];
        int count = 0;
        for (E toy : toyStorage) {
            id[count] = toy.getToyID();
            chance[count] = toy.getFrequency();
            count++;
        }
        int chanceSum = IntStream.of(chance).sum();
        for (int i = 0; i < chance.length; i++) {
            int probabilityNum = (int) Math.round(chance[i] * 100d / chanceSum);
            probability[i] = probabilityNum;
            System.out.println("Совокупная Вероятность выпадения игрушки с ID # " + id[i] + ":  \t" + probabilityNum + "%");
        }
        Arrays.sort(probability);
        Random random = new Random();
        int rnd = random.nextInt(1, 101);
        String name = "";
        int indexStart = 0;
        int indexFinish = 0;
        for (int i = 0; i < probability.length; i++) {
            if (i == 0) {
                indexStart = 0;
                indexFinish = probability[i];
            } else {
                indexStart = indexFinish + 1;
                indexFinish += probability[i];
            }
            if (indexStart <= rnd && rnd <= indexFinish) {
                for (E toy : toyStorage) {
                    int id1 = toy.getToyID();
                    if (id1 == id[i]) {
                        name = toy.getToyName();
                        toy.setQuantity(toy.getQuantity() - 1);
                        if (toy.getQuantity() == 0) {
                            DeleteToy(id1);
                        }
                        updatePrizeList(toy);
                    }
                }
            }
        }
        return "выиграна игрушка: " + name;
    }

    private void updatePrizeList(E toy) throws IOException {
        Toy toy2 = new Toy(toy.getToyID(), toy.getToyName(), 1, 0);
        PrizeList.addPrize((E) toy2);
        SavePrizeStorage();
    }

    public String DeleteToy(int Id) {
        for (E toy : toyStorage) {
            if (Id == toy.getToyID()) {
                toyStorage.remove(toy);
            }
        }
        return "игрушка удалена\n";
    }

    public String GetPrize() throws IOException, ClassNotFoundException {
        if (!PrizeList.getToyStorage().isEmpty()) {
            Toy toyOut = PrizeList.getToyStorage().get(0);
            PrizeList.getToyStorage().remove(0);
            SavePrizeStorage();
            StringBuilder sb = new StringBuilder();
            sb.append("выдано: ");
            sb.append(toyOut.getToyName());
            sb.append(" ,");
            sb.append(toyOut.getQuantity());
            sb.append(" шт.");
            sb.append("\n");
            try (FileWriter writer = new FileWriter("gifts.txt", true)) {
                writer.write(String.valueOf(sb));
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return "игрушка выдана ";
        }
        return "список выдачи пуст";
    }
}

