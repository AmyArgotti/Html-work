package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "reallyStrongPwd123";
    private static final String DB_URL = "jdbc:postgresql://localhost:54321/postgres";

    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from users");

        while (result.next()) {
            System.out.println(result.getInt("user_id") + " " + result.getString("first_name")+" "+result.getString("second_name")+" "+result.getInt("age"));
        }

        Scanner scanner = new Scanner(System.in);
        String firstName = scanner.nextLine();
        String secondName = scanner.nextLine();
        int age = scanner.nextInt();


        String sqlInsertUser = "insert into users(first_name, second_name, age ) values (?,?,?);";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertUser);
        preparedStatement.setString(1,firstName);
        preparedStatement.setString(2,secondName);
        preparedStatement.setInt(3,age);


       /* int affectedRows = statement.executeUpdate(sqlInsertUser);
        preparedStatement.executeUpdate(sqlInsertUser);*/
        int affectedRows = preparedStatement.executeUpdate();
        System.out.println("было добавлено" + affectedRows + "строк");
    }
}