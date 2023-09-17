package employee.crud.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection 
{
	public static final String dbURL = "jdbc:mysql://localhost:3306/employee_db";
	public static final String dbUserName = "root";
	public static final String dbPassword = "Imapro199-";	
	
	public static Connection getConnection()
	{
		System.out.println("Start getConnection"); //Logger : Log4j
		
		try
		{
			//Load MySql JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//Open Connection
			connection = DriverManager.getConnection(dbURL, dbUserName, dbPassword);
			
			if( connection != null)
			{
				System.out.println("Connected !");
				return connection;
			}
			else
			{
				System.out.println("Connection Issue");
				return null;
			}			
		}
		catch (Exception e) 
		{
			System.out.println("Exception in DB Connection==>" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public static Connection connection = getConnection();
	
	
	public static void main(String[] args) {
		System.out.println(DBConnection.connection);
	}
	
}
