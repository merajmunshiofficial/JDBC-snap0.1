package com.pcsglobal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetExamples 
{

	public static void main(String[] args) 
	{
		modifyUserName();
	}
	
	private static void modifyUserName() 
	{
        String QUERY = "select id,name,email,country,password from Users1 where id = 1";
        
        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/jsp?useSSL=false", "root", "password");
            
        	// Step 2:Create a statement using connection object
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            // Step 3: Execute the query or update query
            ResultSet rs = stmt.executeQuery(QUERY)) 
        	{
            	// Step 4: Process the ResultSet object.
	            while (rs.next()) 
	            {
	                String person = rs.getString("name");
	                System.out.println("User name before update : " + person);
	                rs.updateString("person", "Tonymoy");
	                rs.updateRow();
	                System.out.println("User name after update  : " + rs.getString("name"));
	            }
        	} catch (SQLException e) 
        		{
        			System.out.println(e.getMessage());
        		}
    }
}

