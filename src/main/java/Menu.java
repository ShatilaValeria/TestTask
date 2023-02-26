import java.util.Locale;
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
        int choice;
        System.out.println("Регистрация: ");
        System.out.print("Введите ваше имя: ");
        String name = scanner.next();
        String nameToLowerCase = name.toLowerCase(Locale.ROOT);
        do {
            System.out.println();
            System.out.println("Желаете при  регестрации указать адрес?");
            System.out.println("Введите номер операции");
            System.out.println("1. Да");
            System.out.println("2. Нет");
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("Введите вашу улицу: ");
                    String street = scanner.next();
                    System.out.print("Введите номер вашего дома: ");
                    String numHome = scanner.next();
                    String address = "ул. " + street + " д. " + numHome;
                    String addressToLowerCase = address.toLowerCase(Locale.ROOT);
                    String currency = currencyMoneyMenu();
                    Account account = new Account(nameToLowerCase, addressToLowerCase, currency);
                    account.registrationRequest();
                    return;
                }
                case 2 -> {
                    String currency = currencyMoneyMenu();
                    Account account = new Account(nameToLowerCase, null, currency);
                    account.registrationRequest();
                    return;
                }
                case 3 -> {
                    System.out.println("Выход");
                    System.exit(0);
                }
                default -> System.out.println("Некорректный ввод данных");
            }
        } while (true);
    }

    public void entryMenu() {
        int choice;
        System.out.println("Добро пожаловать");
        System.out.print("Введите ваше имя:");
        String name = scanner.next();
        String nameToLowerCase = name.toLowerCase(Locale.ROOT);
        do {
            System.out.println();
            System.out.println("Был ли при регистрации указан адрес?");
            System.out.println("Введите номер операции");
            System.out.println("1. Да");
            System.out.println("2. Нет");
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("Введите вашу улицу: ");
                    String street = scanner.next();
                    System.out.print("Введите номер вашего дома: ");
                    String numHome = scanner.next();
                    String address = "ул. " + street + " д. " + numHome;
                    String addressToLowerCase = address.toLowerCase(Locale.ROOT);
                    String currency = currencyMoneyMenu();
                    Account account = new Account(nameToLowerCase, addressToLowerCase, currency);
                    account.userVerificationRequest();
                    return;
                }
                case 2 -> {
                    String currency = currencyMoneyMenu();
                    Account account = new Account(nameToLowerCase, null, currency);
                    account.userVerificationRequest();
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
                    return;
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
        System.out.print("Введите сумму пополнения\n" +
                "Разделение целого от дробного при помощи ','\n");
        double money = scanner.nextDouble();
        if (money >= 100000000) {
            System.out.println("Операция невозможна \n" +
                    "Размер суммы не может привышать 100'000'000");
            return;
        } else if(money * 10000 % 10 == 0) {
            String currencyMoney;
            do {
                System.out.println();
                System.out.println("Валюта:");
                System.out.println("1. BYN");
                System.out.println("2. USD ");
                System.out.println("3. EUR");
                System.out.println("4. RYB");
                int currency = scanner.nextInt();
                switch (currency) {
                    case 1 -> {
                        currencyMoney = "BYN";
                        account.requestForReplenishment(money, currencyMoney);
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
        } else {
            System.out.println("Дробная часть чисел может быть ограничена 3 знаками\n " +
                    "У вас больше\n" +
                    "Разделение целого от дробного при помощи ','");
        }
    }

    public void withdrawalOfFoundsMenu() {
        System.out.print("Введите ваше имя:");
        String name = scanner.next();
        Account account = new Account(name);
        System.out.print("Введите сумму снятия:");
        double money = scanner.nextDouble();
        if (money >= 100000000) {
            System.out.println("Операция невозможна \n" +
                    "Размер суммы не может привышать 100'000'000");
            return;
        } else if((money * 10000) % 10 != 0) {
            System.out.println("Дробная часть чисел может быть ограничена 3 знаками\n " +
                    "У вас больше\n" +
                    "Разделение целого от дробного при помощи ','");
            return;
        } else {
            account.withdrawalOfFounds(name, money);
        }
    }

    public void checkingTheBalanceMenu() {
        System.out.print("Введите ваше имя:");
        String name = scanner.next();
        Account account = new Account(name);
        account.checkingTheBalance();
    }

    public String currencyMoneyMenu(){
        String currencyMoney;
        do {
            System.out.println();
            System.out.println("Выбор валюты на данном аккаунте:");
            System.out.println("1. BYN");
            System.out.println("2. USD ");
            System.out.println("3. EUR");
            System.out.println("4. RYB");
            int currency = scanner.nextInt();
            switch (currency) {
                case 1 -> {
                    currencyMoney = "BYN";
                    return currencyMoney;
                }
                case 2 -> {
                    currencyMoney = "USD";
                    return currencyMoney;
                }
                case 3 -> {
                    currencyMoney = "EYR";
                    return currencyMoney;
                }
                case 4 -> {
                    currencyMoney = "RYB";
                    return currencyMoney;
                }
                default -> System.out.println("Некорректный ввод данных");
            }
        } while (true);
    }
}