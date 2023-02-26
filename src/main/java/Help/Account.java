package Help;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Account {
    Scanner scanner = new Scanner(System.in);
    Menu menu = new Menu();


    private String name;
    private String address;

    public Account(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Account(String name) {
        this.name = name;
    }

    public Account() {
    }

    public void registrationRequest() {
        Connection connection = new ConnectionSQL().connection();
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Users(name, address)" +
                    "VALUES('" + name + "', '" + address + "')");
            statement.executeUpdate("INSERT INTO Accounts(userId,  balance, currency)" +
                    "VALUES (" +
                    "(SELECT userId FROM Users WHERE  name='" + name + "') ,'" + 0 + "', 0);");
            statement.close();
            connection.close();
            menu.mainMenu();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void userVerificationRequest() {
        Connection connection = new ConnectionSQL().connection();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Users WHERE  name='"
                    + getName() + "' AND address='" + getAddress() + "';");
            while (resultSet.next()) {
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

    public void requestForReplenishment(double money, String currencyMoney) {
        Connection connection = new ConnectionSQL().connection();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Users WHERE  name='" +
                    getName() + "';");
            while (resultSet.next()) {
                int id = resultSet.getInt("userId");
                resultSet = statement.executeQuery("SELECT * FROM Accounts WHERE userId='"
                        + id + "';");
                while (resultSet.next()) {
                    double balance = resultSet.getDouble("balance");
                    String currency = resultSet.getString("currency");
                    int accountId = resultSet.getInt("accountId");
                    statement.executeUpdate("UPDATE Transactions SET accountId ='" + accountId + "';");
                    statement.executeUpdate("UPDATE Transactions SET amount ='" + money + "'" +
                            "WHERE accountId ='" + accountId + "';");
                    resultSet = statement.executeQuery("SELECT * FROM Transactions WHERE accountId='"
                            + accountId + "';");
                    while (resultSet.next()) {
                        double amount = resultSet.getDouble("amount");
                        double i = balance + amount;
                        if (i <= 2000000000) {  //(currency.equals(currencyMoney) || currency == null)
                            statement.executeUpdate("UPDATE Accounts SET balance = '" + i + "'" +
                                    "WHERE userId = '" + id + "';");
                            statement.executeUpdate("UPDATE Accounts SET currency ='" + currencyMoney + "'" +
                                    "WHERE userId = '" + id + "';");
                            statement.executeUpdate("UPDATE Transactions SET amount ='" + 0 + "'" +
                                    "WHERE accountId ='" + accountId + "';");
                            System.out.println(getName() + " ваш баланс успешно пополнен");
                        } else {
                            System.out.println("Проблемы с валютой");
                        }
                        statement.close();
                        connection.close();
                        menu.mainMenu();
                    }
                }
                System.out.println("Что-то не так");
            }
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
    }

    public void withdrawalOfFounds(String name, double money) {
        Connection connection = new ConnectionSQL().connection();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Users WHERE  name='" +
                    name + "';");
            int id = resultSet.getInt("userId");
            while (resultSet.next()) {
                resultSet = statement.executeQuery("SELECT * FROM Accounts WHERE userId='"
                        + id + "';");
                while (resultSet.next()) {
                    double balance = resultSet.getInt("balance");
                    int accountId = resultSet.getInt("accountId");
                    statement.executeUpdate("UPDATE Transactions SET accountId ='" + accountId + "';");
                    statement.executeUpdate("UPDATE Transactions SET amount ='" + money + "'" +
                            "WHERE accountId ='" + accountId + "';");
                    resultSet = statement.executeQuery("SELECT * FROM Transactions WHERE accountId='"
                            + accountId + "';");
                    while (resultSet.next()) {
                        double amount = resultSet.getInt("amount");
                        if (balance >= amount) {
                            double moneyNow = balance - amount;
                            statement.executeUpdate("UPDATE Accounts SET balance = '" + moneyNow + "'" +
                                    "WHERE userId = '" + id + "';");
                            System.out.println(getName() + " ваши деньги...");
                            statement.executeUpdate("UPDATE Transactions SET amount ='" + 0 + "'" +
                                    "WHERE accountId ='" + accountId + "';");
                        } else {
                            System.out.println("Недостаточно средств!");
                        }
                        statement.close();
                        connection.close();
                        menu.mainMenu();
                    }
                }
            }
            System.out.println("Что-то не так");
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
    }

    public void checkingTheBalance() {
        Connection connection = new ConnectionSQL().connection();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Users WHERE  name='" +
                    getName() + "';");
            while (resultSet.next()) {
                int id = resultSet.getInt("userId");
                resultSet = statement.executeQuery("SELECT * FROM Accounts WHERE userId='"
                        + id + "';");
                while (resultSet.next()) {
                    int balance = resultSet.getInt("balance");
                    System.out.println("Ваш баланс:" + balance);
                    statement.close();
                    connection.close();
                    menu.mainMenu();
                }
            }
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
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



