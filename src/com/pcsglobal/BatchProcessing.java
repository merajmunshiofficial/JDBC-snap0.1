package com.pcsglobal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchProcessing 
{
	private static final String INSERT_USERS_SQL = "INSERT INTO users1" +
	        "  (id, name, email, country, password) VALUES " +
	        " (?, ?, ?, ?, ?);";

	private static final String COUNT_RECORDS = "SELECT COUNT(*) FROM users1";
	
	public static void main(String[] args) throws SQLException, IOException
	{
		try (   Connection connection = DriverManager
				// Step 1: Establishing a Connection
	            .getConnection("jdbc:mysql://localhost:3306/jsp?useSSL=false", "root", "password");
				// Step 2:Create a statement using connection object
	            PreparedStatement ps = connection.prepareStatement(INSERT_USERS_SQL);
			)
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
			while (true) 
			{
			  try {
					System.out.println("Enter ID: ");
					String s_id = "";
					s_id = br.readLine();
					System.out.println("Enter Name: ");  
					String name = "";  
					name = br.readLine();
					System.out.println("Enter E-mail: ");  
					String email = "";  
					email = br.readLine();
					System.out.println("Enter Country: ");  
					String country = "";  
					country = br.readLine();
					System.out.println("Enter Password: ");  
					String password = ""; 
					password = br.readLine();	
					int id = Integer.parseInt(s_id); 
					ps.setInt(1, id);
					ps.setString(2, name);
					ps.setString(3,email);
					ps.setString(4, country);
					ps.setString(5, password);
					
				} catch (IOException e) { e.printStackTrace(); }
			  
				// Keep adding the records as a Batch job
				ps.addBatch();
				
				// Continue adding more records?
				System.out.println("Want to add more records y/n");  
				String ans = "y";
				try 
				{
					ans = br.readLine();
				} catch (IOException e) { e.printStackTrace(); } 
				
				if(ans.equals("n"))  
					break;  
			}  // while loop
			
			// Execute the entire batch of records together as a batch job, so all inserts go as one task to hit the database
			int[] count = ps.executeBatch();// for executing the batch  
			System.out.println("All Records successfully saved : ");
			System.out.printf("%d row(s) inserted!\n ", count.length);
			PreparedStatement pstmt = connection.prepareStatement(COUNT_RECORDS);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int countRows = rs.getInt(1); 
			System.out.println("Total No. of records in table -> " + countRows);
			
		} catch(SQLException e) 
		  {
		        // print SQL exception information
				System.out.println(e.getMessage());
		  }
	}  // main
}  // class
