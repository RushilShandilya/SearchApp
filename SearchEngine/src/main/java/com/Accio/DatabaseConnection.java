package com.Accio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    static Connection connection = null;
    public static Connection getConnection(){
        if(connection!=null) return connection;

        String user = "root";
        String pwd = "";
        String db = "SearchEngineApp";

        return getConnection(user,pwd,db);
    }
    private static Connection getConnection(String user , String password , String database){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://localhost:3306/"+database;
            connection = DriverManager.getConnection(url,user,password);
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
