package ui.Commands;

import ui.View;

public class PrintToyStorage implements Command {
    public View view;

    public PrintToyStorage(View view) {
        this.view = view;
    }

    @Override
    public String getDescription() {
        return "распечатать склад игрушек";
    }

    @Override
    public void execute() {
        view.PrintToyStorage();
    }
}

