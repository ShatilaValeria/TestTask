import java.sql.SQLException;
import java.util.Scanner;

public class CallingTheMenu {
    Scanner scanner = new Scanner(System.in);

    private String name ;
    private String address;

    public void startMenu() {
        int choice;
        System.out.println("Добро пожаловать");
        do {
            System.out.println();
            System.out.println("Введите номер операции");
            System.out.println("1. Войти в существующий аккаунт");
            System.out.println("2. Регистрация ");
            System.out.println("3. Выход ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    this.entryMenu();
                }
                case 2 -> {
                    this.registrationMenu();
                    //return;
                }
                case 3 -> {
                    System.out.println("Выход");
                    return;
                }
                default -> System.out.println("Некорректный ввод данных");

            }
        } while (true);
    }

    public void registrationMenu() {
        System.out.println("Регистрация");
        System.out.print("Введите ваше имя: ");
        name = scanner.next();
        System.out.print("Введите вашу улицу: ");
        String street = scanner.next();
        System.out.print("Введите номер вашего дома: ");
        String numHome = scanner.next();
        address = street + numHome;
        setName(name);
        setAddress(address);
        Add add = new Add(name, address);
        try {
            add.registrationRequest(name, address);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void mainMenu() {
        int choice;
        do {
            System.out.println();
            System.out.println("Введите номер операции");
            System.out.println("1. Пополнение счёта");
            System.out.println("2. Снятие ");
            System.out.println("3. Баланс");
            System.out.println("4. Выйти");
            System.out.println();
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    this.enrollmentMenu();
                }
                case 2 -> {
                    System.out.println("Выход");
                    return;
                }
                case 3 -> {
                    System.out.println("Выход");
                    return;
                }
                case 4 -> {
                    System.out.println("Выход");
                    return;
                }
                default -> System.out.println("Некорректный ввод данных");
            }
        } while (true);
    }

    public void entryMenu() {

        System.out.println("Добро пожаловать");
        System.out.print("Введите ваше имя:");
        String name = scanner.next();
        System.out.print("Введите вашу улицу: ");
        String street = scanner.next();
        System.out.print("Введите номер вашего дома: ");
        String numHome = scanner.next();
        String address = street + numHome;
        Add add = new Add(name, address);

        add.userVerificationRequest(name, address);
    }

    public void enrollmentMenu() {
        name = getName();
        System.out.println(name);
        System.out.print("Введите сумму пополнения:");
        Integer money = scanner.nextInt();
        String currencyMoney;
        do {
            System.out.println();
            System.out.println("Валюта:");
            System.out.println("1. BYN");
            System.out.println("2. USD ");
            System.out.println("3. EUR");
            System.out.println("4. RYB");
            Integer currency = scanner.nextInt();
            switch (currency) {
                case 1 -> {
                    currencyMoney = "BYN";
                    //add.requestForReplenishment(name, money, currencyMoney);
                    return;
                }
                case 2 -> {
                    currencyMoney = "USD";
                    return;
                }
                case 3 -> {
                    currencyMoney = "EYR";
                    return;
                }
                case 4 -> {
                    currencyMoney = "RYB";
                    return;
                }
                default -> System.out.println("Некорректный ввод данных");
            }
        }while(true);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
