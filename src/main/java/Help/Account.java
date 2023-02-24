package Help;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Account {

    private String name;
    private String address;

    public Account(String name, String address) {
        this.name = name;
        this.address = address;
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
        Connection connection = new ConnectionSQL().connection();
        Statement statement;

        try {
            statement = connection.createStatement();
            statement.executeUpdate("SELECT * FROM  Users" +
                    "WHERE name IN ('" + name + "');");
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//        statement.execute("SELECT * FROM  Users" +
//                "WHERE name LIKE '" + name + "' AND  address LIKE '" + address + "';");
    }

    public void requestForReplenishment( Integer money, String currencyMoney) throws SQLException {
        Connection connection = new ConnectionSQL().connection();
        Statement statement = null;
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
        while(resultSet.next()){

            int id = resultSet.getInt("userId");
            String name = resultSet.getString("ProductName");

        }

        System.out.println(getName());

        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE Accounts SET balance = '"+ money +"'" +
                    "WHERE accountId = 12;");
            statement.executeUpdate("UPDATE Accounts SET currency ='" + currencyMoney + "'" +
                    "WHERE accountId = 12;");


            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
