/**
 * @author ZHANG Wengyu
 * COMP2411 LAB06
 * compile and run with:
 *      javac -cp ojdbc8.jar EmployeeDB.java && java -cp ojdbc8.jar:. EmployeeDB
 */

import java.io.*;
import java.io.Console;
import java.sql.*;
import oracle.jdbc.driver.*;
import oracle.sql.*;

public class EmployeeDB {
    private static OracleConnection connection;
    private static String username;
    private static String pwd;

    /**
     * Login page
     * to get username and password
     */
    public static void login() {
        Console console = System.console();
		System.out.print("Enter your username: ");
		username = console.readLine();
		System.out.print("Enter your password: ");
		char[] password = console.readPassword();
		pwd = String.valueOf(password);
    }

    /**
     * initialize the connection to the database
     */
    public static void initializeConnection() throws SQLException, IOException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String url = "jdbc:oracle:thin:@studora.comp.polyu.edu.hk:1521:dbms";
        connection = (OracleConnection)DriverManager.getConnection(url,username,pwd);
    }

    /**
     * close the connection
     */
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    /**
     * Query all employees and list them out
     */
    public static void listAllEmployees() throws SQLException {
        Statement statement = connection.createStatement();
        String inSql = "SELECT ENO, ENAME FROM EMPLOYEES";
        ResultSet resultSet = statement.executeQuery(inSql);
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1)+ " " + resultSet.getString(2));
        }
        System.out.println();
    }

    /**
     * Query one employee with eENO and list out
     * @param inNum: input employee number
     */ 
    public static void listEmployeeByNumber(int inNum) throws SQLException {
        String inSql = "SELECT * FROM EMPLOYEES WHERE ENO = ?";
        PreparedStatement ps = connection.prepareStatement(inSql);
        ps.setInt(1,inNum);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " " +
            resultSet.getString(2)+ " " +
            resultSet.getInt(3)+ " " +
            resultSet.getString(4).substring(0, 10));
        }
        System.out.println();
    }

    public static void main(String args[]) throws SQLException, IOException {
        login(); // login page
        initializeConnection(); // initialize connection to DB
        listAllEmployees(); // list all employees first
        while (true) {
            // listen user input of the employee number
            Console console = System.console();
            System.out.print("employee number: ");
            String inNum = console.readLine();

            // List out the target employee
            listEmployeeByNumber(Integer.parseInt(inNum));

            // Ask user if wants to continue or exit
            System.out.print("Enter a number to continue or -1 to exit: ");
            String flag = console.readLine();
            if (flag.equals("-1")) {
                System.out.println("Bye! Have a nice day!");
                break;
            }
        }
        closeConnection(); // close connection
    }
}