package ui.Commands;

import ui.View;

import java.io.IOException;

public class SaveToyStorage implements Command {
    public View view;

    public SaveToyStorage(View view) {
        this.view = view;
    }

    @Override
    public String getDescription() {
        return "сохранить склад игрушек:";
    }

    @Override
    public void execute() throws IOException {
        view.SaveToyStorage();
    }
}
