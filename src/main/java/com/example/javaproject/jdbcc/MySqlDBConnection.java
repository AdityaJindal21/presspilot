package com.example.javaproject.jdbcc;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlDBConnection {
    public static Connection getMySqlDBConnection()
    {
        Connection con=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/dbjavaproject","root","jindal#2027@aditya");
        }catch (Exception e)
        {
            System.out.println(e.toString());
        }
        return con;
    }
}
