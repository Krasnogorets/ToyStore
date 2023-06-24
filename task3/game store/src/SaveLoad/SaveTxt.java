package SaveLoad;

import java.io.*;

public class SaveTxt implements Saveable {
    @Override
    public boolean save(Serializable obj, String file) throws IOException {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(file));
            objectOutputStream.writeObject(obj);
            objectOutputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Serializable load(String file) throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(
                    new FileInputStream(file));
            return (Serializable) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}