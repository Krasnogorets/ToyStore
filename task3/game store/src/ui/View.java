package ui;

import Presenter.Presenter;

import java.io.IOException;

public interface View {
    void print(String text);

    void start() throws IOException, ClassNotFoundException;

    void setPresenter(Presenter presenter);

    void SaveToyStorage() throws IOException;

    void PrintToyStorage();

    void AddToy();

    void ToyLottery() throws IOException;

    void GetPrize() throws IOException, ClassNotFoundException;

    void ChangeToyInfo();

    void finish();
    void LoadStorage() throws IOException, ClassNotFoundException;
    void DeleteToy();

}
