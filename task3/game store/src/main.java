import Gamestore.Storage;
import ui.View;
import Gamestore.Toy;
import ui.ConsoleUI;
import Presenter.Presenter;
import java.io.IOException;

public class main {
    static Storage<Toy> gameStore = new Storage<>();
    static Storage<Toy> prizes = new Storage<>();
        public static void main(String[] args) throws IOException, ClassNotFoundException {

            View view = new ConsoleUI();
            Gamestore.Service<Toy> service = new Gamestore.Service<>(gameStore,prizes);
            new Presenter(view, service);
            view.start();
        }
}
