package com.pcsglobal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertPStatementExample 
{
	private static final String INSERT_USERS_SQL = "INSERT INTO users1" +
	        "  (id, name, email, country, password) VALUES " +
	        " (?, ?, ?, ?, ?);";

	public static void main(String[] argv) throws SQLException 
	{
	    InsertPStatementExample createTableExample = new InsertPStatementExample();
	    createTableExample.insertRecord();
	}

	    public void insertRecord() throws SQLException 
    {
	        System.out.println(INSERT_USERS_SQL);
	        // Step 1: Establishing a Connection
	        try (Connection con = DriverManager
	            .getConnection("jdbc:mysql://localhost:3306/jsp?useSSL=false", "root", "password");

	            // Step 2:Create a statement using connection object
	            PreparedStatement pstmt = con.prepareStatement(INSERT_USERS_SQL)) 
	        	{
		            pstmt.setInt(1, 1);
		            pstmt.setString(2, "Tony");
		            pstmt.setString(3, "tony@gmail.com");
		            pstmt.setString(4, "US");
		            pstmt.setString(5, "secret");
	
		            System.out.println(pstmt);
		            // Step 3: Execute the query or update query
		            pstmt.executeUpdate();
		        } catch (SQLException e) 
		          {
		            // print SQL exception information
		            System.out.println(e.getMessage());
		           }

	        // Step 4: try-with-resource statement will auto close the connection.
	    }
  }
