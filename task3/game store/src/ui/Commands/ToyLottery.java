package ui.Commands;

import ui.View;

import java.io.IOException;

public class ToyLottery implements Command {
    public View view;

    public ToyLottery(View view) {
        this.view = view;
    }

    @Override
    public String getDescription() {
        return "Провести розыгрыш игрушки:";
    }

    @Override
    public void execute() throws IOException, ClassNotFoundException {
        view.ToyLottery();
    }
}
