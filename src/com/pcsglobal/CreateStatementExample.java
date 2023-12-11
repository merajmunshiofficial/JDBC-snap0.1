package com.pcsglobal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateStatementExample 
{
    		private static final String createTableSQL = 
    				"drop table if exists users1; \r\n " +
    				"create table users1(\r\n" + 
    				"  id  int(3) primary key,\r\n" +
            		"  name varchar(20),\r\n" + 
    				"  email varchar(20),\r\n" + 
            		"  country varchar(20),\r\n" +
            	"  password varchar(20)\r\n" + "  );";
	public static void main(String[] args) throws SQLException 
	{
		CreateStatementExample createTableExample = new CreateStatementExample();
           createTableExample.createTable();
	}
	
	public void createTable() throws SQLException 
	{
	        System.out.println(createTableSQL);
	        // Step 1: Establishing a Connection
	        try (Connection con = DriverManager
	            .getConnection("jdbc:mysql://localhost:3306/jsp?allowMultiQueries=TRUE &amp useSSL=false", "root", "password");

	            // Step 2:Create a statement using connection object
	            Statement stmt = con.createStatement();) 
	        {
		            // Step 3: Execute the query or update query
		            stmt.execute(createTableSQL);
	        } catch (SQLException e) {}

	        // Step 4: try-with-resource statement will auto close the connection.
	 }
}
