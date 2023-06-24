package ui;

import Presenter.Presenter;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleUI implements View {
    protected Presenter presenter;
    protected Scanner scanner;
    protected Menu menu;
    protected boolean flag;

    public ConsoleUI() {
        scanner = new Scanner(System.in);
        flag = true;
        menu = new Menu(this);
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void SaveToyStorage() throws IOException {
        presenter.SaveToyStorage();
        System.out.println("Склад сохранен в файл. ");
    }

    @Override
    public void ToyLottery() throws IOException {
        presenter.ToyLottery();
        presenter.SaveToyStorage();
        presenter.printStorage();
    }

    @Override
    public void GetPrize() throws IOException, ClassNotFoundException {
        presenter.printPrizeList();
        System.out.print("Введите Y -для выдачи игрушки:");
        String choice = scanner.nextLine().toLowerCase();
        if (choice.equals("y") || choice.equals("н")) {
            presenter.GetPrize();
        }
    }

    @Override
    public void PrintToyStorage() {
        System.out.println("распечатать склад игрушек:");
        presenter.printStorage();
    }

    @Override
    public void finish() {
        System.out.println("Работа завершена");
        flag = false;
    }

    @Override
    public void start() throws IOException, ClassNotFoundException {
        System.out.println("Проект магазин игрушек");
        System.out.println("Загрузите склад игрушек из файла или создайне новый, путем добавления игрушек.\n");
        while (flag) {
            printMenu();
            System.out.print("Введите пункт меню:");
            execute();
        }
    }

    public void printMenu() {
        System.out.println(menu.print());
    }

    @Override
    public void print(String text) {
        System.out.println(text);
    }

    private void execute() throws IOException, ClassNotFoundException {
        String inputLine = scanner.nextLine();
        if (checkInput(inputLine)) {
            int choice = Integer.parseInt(inputLine);
            if (checkChoice(choice)) {
                menu.execute(choice);
            }
        }
    }

    @Override
    public void LoadStorage() throws IOException, ClassNotFoundException {
        presenter.LoadStorage();
        System.out.println("Склад игрушек загружен из файла. ");
        presenter.printStorage();
    }

    private boolean checkInput(String text) {
        if (text.matches("[0-9]+")) {
            return true;
        } else {
            System.out.println("Неверный ввод команды");
            return false;
        }
    }

    private boolean checkChoice(int choice) {
        if (choice <= menu.size()) {
            return true;
        } else {
            System.out.print("Неверный ввод команды");
            return false;
        }
    }

    @Override
    public void AddToy() {
        System.out.print("Введите название игрушки: ");
        String toyName = scanner.nextLine();
        System.out.print("Введите количество игрушек (целое число): ");
        int quantity = scanner.nextInt();
        System.out.print("Введите частоту выпадения игрушки (вес в % от 100): ");
        int frequency = scanner.nextInt();
        String f = scanner.nextLine();
        presenter.AddToy(1, toyName, quantity, frequency);
    }

    @Override
    public void ChangeToyInfo() {
        System.out.print("Введите id игрушки: ");
        int toyID = scanner.nextInt();
        System.out.print("Введите изменения в частоту выпадения игрушки (вес в % от 100): ");
        int frequency = scanner.nextInt();
        String f = scanner.nextLine();
        presenter.ChangeToyInfo(toyID, frequency);
    }

    @Override
    public void DeleteToy() {
        System.out.print("Введите Id игрушки для удаления: ");
        int toyId = scanner.nextInt();
        String f = scanner.nextLine();
        presenter.DeleteToy(toyId);
    }
}
