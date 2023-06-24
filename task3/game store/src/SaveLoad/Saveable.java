package SaveLoad;

import java.io.IOException;
import java.io.Serializable;

public interface Saveable {

    boolean save(Serializable obj, String file) throws IOException;

    Serializable load(String file) throws IOException, ClassNotFoundException;
}
