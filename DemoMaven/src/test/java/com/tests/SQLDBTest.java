package com.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

public class SQLDBTest extends TestBase {

	@Test
	public void DbConnectTest() throws SQLException{

		
		//Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
		
		String dbUrl = "jdbc:mysql://192.168.43.220:3036/employees";
		String username = "root";

		String password="Coke@123";
		
		//Load mysql jdbc driver
		//Class.forName("com.mysql.jdbc.Driver");
		
		//Query to Execute
		String query = "select * from departments;";
		
		//Create Connection to DB	
		Connection con = DriverManager.getConnection(dbUrl, username, password);
		
		//Create Statement Object		
		Statement stmt = con.createStatement();					

		// Execute the SQL Query. Store results in ResultSet		
  		ResultSet rs= stmt.executeQuery(query);							
  
  		// While Loop to iterate through all data and print results		
			while (rs.next()){
		        	 String Deptno = rs.getString(1);								        
                     String DeptName = rs.getString(2);					                               
                     System. out.println(Deptno+"  "+DeptName);	
				
	}

}
	
}
