package ui.Commands;

import ui.View;

import java.io.IOException;

public class DeleteToy implements Command {
    public View view;

    public DeleteToy(View view) {
        this.view = view;
    }

    @Override
    public String getDescription() {
        return "Удалить игрушку:";
    }

    @Override
    public void execute() throws IOException, ClassNotFoundException {
        view.DeleteToy();
    }
}
