package Help;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Account {
    Scanner scanner = new Scanner(System.in);

    private String name;
    private String address;

    public Account(String name, String address) {
        this.name = name;
        this.address = address;
    }
    public Account(){
    }

    public boolean registrationRequest() {
        Connection connection = new ConnectionSQL().connection();
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Users(name, address)" +
                    "VALUES('" + name + "', '" + address + "')");
            statement.executeUpdate("INSERT INTO Accounts(userId,  balance, currency)" +
                    "VALUES (" +
                    "(SELECT userId FROM Users WHERE  name='" + name + "') , 0, 0);");
            setName(name);
            statement.close();
            connection.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public void userVerificationRequest() {
        Menu menu = new Menu();
        Connection connection = new ConnectionSQL().connection();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Users WHERE  name='"
                    + getName() + "' AND address='" + getAddress() + "';");
            while (resultSet.next()) {

               setName(name);
                System.out.println(getName() + " вы успешно вошли в свой аккаунт");
                statement.close();
                connection.close();
                menu.mainMenu();
            }
            System.out.println("У нас нет такого пользователя!");
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
//            statement.executeQuery("SELECT * FROM Users WHERE  name LIKE'" + name + "'");
//            statement.execute("SELECT * FROM  Users" +
//                "WHERE name LIKE '" + name + "' AND  address LIKE '" + address + "';");
    }

    public void requestForReplenishment(Integer money, String currencyMoney) {
        Connection connection = new ConnectionSQL().connection();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Users WHERE  name='" +
                    getName() + "';");
            while (resultSet.next()) {
                int id = resultSet.getInt("userId");
                String name = resultSet.getString("name");

                statement.executeUpdate("UPDATE Accounts SET balance = '" + money + "'" +
                        "WHERE userId = '"+ id + "';");
                statement.executeUpdate("UPDATE Accounts SET currency ='" + currencyMoney + "'" +
                        "WHERE userId = '"+ id + "';");

                statement.close();
                connection.close();
                System.out.println(name + " ваш баланс успешно пополнен");
            }

        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
    }

//    public void enrollmentMenu() {
//        System.out.print("Введите сумму пополнения:");
//        Integer money = scanner.nextInt();
//        String currencyMoney;
//        do {
//            System.out.println();
//            System.out.println("Валюта:");
//            System.out.println("1. BYN");
//            System.out.println("2. USD ");
//            System.out.println("3. EUR");
//            System.out.println("4. RYB");
//            Integer currency = scanner.nextInt();
//            switch (currency) {
//                case 1 -> {
//                    currencyMoney = "BYN";
//                    requestForReplenishment(money, currencyMoney);
//                    return;
//                }
//                case 2 -> {
//                    currencyMoney = "USD";
//                    requestForReplenishment(money, currencyMoney);
//                    return;
//                }
//                case 3 -> {
//                    currencyMoney = "EYR";
//                    requestForReplenishment(money, currencyMoney);
//                    return;
//                }
//                case 4 -> {
//                    currencyMoney = "RYB";
//                    requestForReplenishment(money, currencyMoney);
//                    return;
//                }
//                default -> System.out.println("Некорректный ввод данных");
//            }
//        }while(true);
//    }

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
