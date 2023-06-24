package ui.Commands;

import ui.View;

public class AddToy implements Command {
    public View view;

    public AddToy(View view) {
        this.view = view;
    }

    @Override
    public String getDescription() {
        return "добавить игрушку:";
    }

    @Override
    public void execute() {
        view.AddToy();
    }
}
