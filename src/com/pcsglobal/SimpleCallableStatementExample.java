package com.pcsglobal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SimpleCallableStatementExample 
{
	public static void main(String[] args) 
	{
        String jdbcUrl = "jdbc:mysql://localhost:3306/jsp?useSSL=false";
        String username = "root";
        String password = "password";
        String sql = "call retreive_users()";

        try (
        	 Connection conn = DriverManager.getConnection(jdbcUrl, username, password); 
        	 CallableStatement callStmt = conn.prepareCall(sql); 
        	 ResultSet rs = callStmt.executeQuery();
        	) 
            {
            	while (rs.next()) 
            	{
            		System.out.println("ID = " + rs.getInt(1) + ", NAME = " + rs.getString(2) + ", Email = " +
                                         rs.getString(3) + ", Country = " + rs.getString(4) + ", Password = " + rs.getString(5));
            	}

        	} catch (SQLException e) 
        	  {
        			e.printStackTrace();
        	  }
    }
}
