package ui.Commands;

import ui.View;

import java.io.IOException;


public class LoadStorage implements Command {
    public View view;

    public LoadStorage(View view) {
        this.view = view;
    }

    @Override
    public String getDescription() {
        return "Загрузить склад игрушек из файла:";
    }

    @Override
    public void execute() throws IOException, ClassNotFoundException {
        view.LoadStorage();
    }
}
