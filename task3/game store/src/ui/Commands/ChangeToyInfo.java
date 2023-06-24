package ui.Commands;

import ui.View;


public class ChangeToyInfo implements Command {
    public View view;

    public ChangeToyInfo(View view) {
        this.view = view;
    }

    @Override
    public String getDescription() {
        return "Изменить инфо об игрушке:";
    }

    @Override
    public void execute() {
        view.ChangeToyInfo();
    }
}
