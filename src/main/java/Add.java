import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Add {

    String requestForAddUser;
    private String name;
    private String address;

    public Add(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void registrationRequest(String name, String address) throws SQLException {
        CallingTheMenu callingTheMenu = new CallingTheMenu();
        Connection connection = new ConnectionSQL().connection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO Users(name, address)" +
                "VALUES('" + name + "', '" + address + "')");

        statement.executeUpdate("INSERT INTO Accounts(userId,  balance, currency)" +
                    "VALUES (" +
                  "(SELECT userId FROM Users WHERE  name='" + name +"') , 0, 0);");
        statement.close();
        connection.close();
        callingTheMenu.mainMenu();
    }

    public void userVerificationRequest(String name, String address) {
        CallingTheMenu callingTheMenu = new CallingTheMenu();
        Connection connection = new ConnectionSQL().connection();
        Statement statement;

        try {
            statement = connection.createStatement();
            statement.executeUpdate("SELECT * FROM  Users" +
                    "WHERE name IN ('" + name + "');");
//        statement.execute("SELECT * FROM  Users" +
//                "WHERE name LIKE '" + name + "' AND  address LIKE '" + address + "';");
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void requestForReplenishment(String name, Integer money, String currencyMoney) {
        CallingTheMenu callingTheMenu = new CallingTheMenu();
        Connection connection = new ConnectionSQL().connection();
        Statement statement;

        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Accounts(userId,  balance, currency)" +
                    "VALUES (" +
                    "(SELECT userId FROM Users WHERE  name='" + name +"') ," + money + ", 0);");


            System.out.println(callingTheMenu.getName());
            System.out.println(callingTheMenu.getAddress());

            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
