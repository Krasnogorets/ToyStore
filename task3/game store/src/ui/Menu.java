package ui;

import ui.Commands.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Command> commandList;
    private View view;

    public Menu(View view) {
        commandList = new ArrayList<>();
        commandList.add(new PrintToyStorage(view));
        commandList.add(new LoadStorage(view));
        commandList.add(new SaveToyStorage(view));
        commandList.add(new AddToy(view));
        commandList.add(new ChangeToyInfo(view));
        commandList.add(new ToyLottery(view));
        commandList.add(new GetPrize(view));
        commandList.add(new DeleteToy(view));
        commandList.add(new Finish(view));
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < commandList.size(); i++) {
            sb.append(i + 1);
            sb.append(". ");
            sb.append(commandList.get(i).getDescription());
            sb.append("\n");
        }

        return sb.toString();
    }

    public void execute(int choice) throws IOException, ClassNotFoundException {
        commandList.get(choice - 1).execute();
    }

    public int size() {
        return commandList.size();
    }

}
