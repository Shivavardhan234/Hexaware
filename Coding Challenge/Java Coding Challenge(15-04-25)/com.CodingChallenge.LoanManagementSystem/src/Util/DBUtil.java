package Util;

import java.sql.*;


public class DBUtil {
	private static final String URL="jdbc:mysql://localhost:3306/LoanManagementSystem";
	private static final String USER="root";
	private static final String PASSWORD="Shivavardhan@t2s";
	private static Connection connection;
	
	private DBUtil() {}
	
	public static Connection getDBConn()  {
		if(connection==null) {
			try {
				connection=DriverManager.getConnection(URL,USER,PASSWORD);
				System.out.println("Connection Established");
			}
			catch(SQLException e) {
				System.err.println("Database connection failed"+ e.getMessage());
			}
		}
		return connection;
	}

}
