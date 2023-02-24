package Help;

import java.util.Scanner;

public class Menu {

    Scanner scanner = new Scanner(System.in);

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
                case 1 -> this.entryMenu();
                case 2 -> this.registrationMenu();
                case 3 -> {
                    System.out.println("Выход");
                    return;
                }
                default -> System.out.println("Некорректный ввод данных");

            }
        } while (true);
    }

    public void registrationMenu() {
        System.out.println("Регистрация: ");
        System.out.print("Введите ваше имя: ");
        String name = scanner.next();
        System.out.print("Введите вашу улицу: ");
        String street = scanner.next();
        System.out.print("Введите номер вашего дома: ");
        String numHome = scanner.next();
        String address = street + numHome;

        Account account = new Account(name, address);
        account.registrationRequest();
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
        Account account = new Account(name, address);
        account.userVerificationRequest();
    }

    public void mainMenu() {
        int choice;
        Account account = new Account();
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
                    this.withdrawalOfFoundsMenu();
                    return;
                }
                case 3 -> {
                    this.checkingTheBalanceMenu();
                    return;
                }
                case 4 -> {
                    System.out.println("Выход");
                    System.exit(0);
                }
                default -> System.out.println("Некорректный ввод данных");
            }
        } while (true);
    }


    public void enrollmentMenu() {
        System.out.print("Введите ваше имя:");
        String name = scanner.next();
        Account account = new Account(name);
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
                    account.requestForReplenishment(money, currencyMoney);
                    return;
                }
                case 2 -> {
                    currencyMoney = "USD";
                    account.requestForReplenishment(money, currencyMoney);
                    return;
                }
                case 3 -> {
                    currencyMoney = "EYR";
                    account.requestForReplenishment(money, currencyMoney);
                    return;
                }
                case 4 -> {
                    currencyMoney = "RYB";
                    account.requestForReplenishment(money, currencyMoney);
                    return;
                }
                default -> System.out.println("Некорректный ввод данных");
            }
        } while (true);
    }

    public void withdrawalOfFoundsMenu() {
        System.out.print("Введите ваше имя:");
        String name = scanner.next();
        Account account = new Account(name);
        System.out.print("Введите сумму снятия:");
        Integer money = scanner.nextInt();
        account.withdrawalOfFounds(name, money);
    }

    public void checkingTheBalanceMenu() {
        System.out.print("Введите ваше имя:");
        String name = scanner.next();
        Account account = new Account(name);
        account.checkingTheBalance();
    }
}
