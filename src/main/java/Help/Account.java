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
    public Account(String name){
        this.name = name;
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
        Menu menu = new Menu();
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
                    Integer balance = resultSet.getInt("balance");
                    String currency = resultSet.getString("currency");
                    int i = balance + money;
                    if (currency == currencyMoney) {
                        statement.executeUpdate("UPDATE Accounts SET balance = '" + i + "'" +
                                "WHERE userId = '" + id + "';");
                        statement.executeUpdate("UPDATE Accounts SET currency ='" + currencyMoney + "'" +
                                "WHERE userId = '" + id + "';");

                        statement.close();
                        connection.close();
                        System.out.println(getName() + " ваш баланс успешно пополнен");
                        menu.mainMenu();
                    } else {
                        System.out.println("Проблемы с валютой");
                    }
                }
            }
            System.out.println("Что-то не так");
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
    }

    public void withdrawalOfFounds(String name, Integer money) {
        Menu menu = new Menu();
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
                    Integer balance = resultSet.getInt("balance");
                    int i = balance - money;
                    if (i >= 0) {
                        statement.executeUpdate("UPDATE Accounts SET balance = '" + i + "'" +
                                "WHERE userId = '" + id + "';");
                    } else {
                        System.out.println("Недостаточно средств!");
                    }
                    statement.close();
                    connection.close();
                    System.out.println(getName() + " ваши деньги...");
                    menu.mainMenu();
                }
            }
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
    }

    public void checkingTheBalance() {
        Menu menu = new Menu();
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
                    Integer balance = resultSet.getInt("balance");
                    System.out.println(balance);
                    statement.close();
                    connection.close();
                    System.out.println(getName() + " ваши деньги...");
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
