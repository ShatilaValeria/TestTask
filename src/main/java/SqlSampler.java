//import java.sql.*;
//import java.util.Scanner;
//
//public class SqlSampler {
//
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        try {
//            Class.forName("org.sqlite.JDBC");
//        } catch (ClassNotFoundException e) {
//            System.out.println("No sqlite driver");
//            return;
//        }
//
//        Connection connection = null;
//        Statement statement = null;
//        try {
//            connection =
//                    DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\test.db");
//            statement = connection.createStatement();
//            //если множество ResultSet
//            // statement.execute("");
//            //один ResultSet
//            ResultSet rs = statement.executeQuery("SELECT * FROM Users ");
//            while (rs.next()) {
//                System.out.println(rs.getString("name"));
//            }
//            //запрос без возвращения результатов
//            // statement.executeUpdate("INSERT INTO students VALUES()");
//
//
//
//           CallingTheMenu callingTheMenu = new CallingTheMenu();
//            Add add = new Add();
//            callingTheMenu.startMenu();
//            System.out.println(add.requestForAddUser);
//   //         System.out.println(add.getRequestForAddUser());
//  //          statement.executeUpdate(add.getRequestForAddUser());
////
////            System.out.println("Регистрация");
////            System.out.print("Введите ваше имя: ");
////            String name = scanner.next();
////            System.out.print("Введите ваш адрес: ");
////            String address = scanner.next();
////            statement.executeUpdate("INSERT INTO Users(name, address)" +
////                   "VALUES('" + name + "', '" + address + "')");
////            statement.executeUpdate("INSERT INTO Accounts(userId,  balance, currency)" +
////                    "VALUES (" +
////                    "(SELECT userId FROM Users WHERE  name='" + name +"') , 0, 0);");
//
//
////            String result = add.getRequest();
////            System.out.println(result);
//          //  statement.executeUpdate(callingTheMenu.registrationMenu());
//
////            System.out.println("Введите ваше имя");
////            String name = scanner.nextLine();
////            System.out.println("Введите ваш адрес");
////            String address = scanner.nextLine();
////            statement.executeUpdate("INSERT INTO Users(name, address)" +
////                    "VALUES('" + name + "', '" + address + "')");
//
//            statement.close();
//            connection.close();
//        }  catch (SQLException throwable) {
//            throwable.printStackTrace();
//        } finally {
//            try {
//                if (statement != null) {
//                    statement.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException ex) {
//                System.out.println("Can't close the connection");
//            }
//        }
//    }
//}
