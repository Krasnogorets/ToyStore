package Presenter;

import Gamestore.Service;
import ui.View;

import java.io.IOException;

public class Presenter {
    protected View view;
    public Service service;

    public Presenter(View view, Gamestore.Service service) {
        this.view = view;
        this.service = service;
        view.setPresenter(this);
    }


    public void setView(View view) {
        this.view = view;
        view.setPresenter(this);
    }


    public void printStorage() {
        view.print(service.printStorage().toString());
    }

    public void printPrizeList() throws IOException, ClassNotFoundException {
        view.print(service.printPrizeList().toString());
    }

    public void DeleteToy(int Id) {
        String answer = service.DeleteToy(Id);
        view.print(answer);
    }


    public void LoadStorage() throws IOException, ClassNotFoundException {
        service.LoadStorage();
    }

    public void SaveToyStorage() throws IOException {
        service.SaveToyStorage();
    }


    public void AddToy(int toyID, String toyName, int quantity, int frequency) {
        String answer = service.AddToy(toyID, toyName, quantity, frequency);
        view.print(answer);
    }


    public void ChangeToyInfo(int toyID, int frequency) {
        String answer = service.ChangeToyInfo(toyID, frequency);
        view.print(answer);
    }

    public void ToyLottery() throws IOException {
        String answer = service.ToyLottery();
        view.print(answer);
    }

    public void GetPrize() throws IOException, ClassNotFoundException {
        String answer = service.GetPrize();
        view.print(answer);
    }
}


