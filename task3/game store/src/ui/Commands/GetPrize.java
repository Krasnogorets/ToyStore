package ui.Commands;

import ui.View;

import java.io.IOException;

public class GetPrize implements Command {
    public View view;

    public GetPrize(View view) {
        this.view = view;
    }

    @Override
    public String getDescription() {
        return "Выдать призовые игрушки:";
    }

    @Override
    public void execute() throws IOException, ClassNotFoundException {
        view.GetPrize();
    }
}
