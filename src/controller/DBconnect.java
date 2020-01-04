package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Boomy
 */
public class DBconnect {

    public static Connection connect() throws ClassNotFoundException, SQLException {
        Connection con;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String URL = "jdbc:sqlserver://localhost:1433;databaseName=testingDb;user=gillian;password=dokidoki";
        con = DriverManager.getConnection(URL);

        return con;
    }

}
