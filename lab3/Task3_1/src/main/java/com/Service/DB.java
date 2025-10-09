package com.Service;

import java.io.IOException;
import java.sql.*;

public class DB {
	// JDBC URL, username and password of MySQL server
	public final String url = "jdbc:postgresql://localhost:5432/Cafe";
	public final String username = "postgres";
	public final String password = "masterkey";
	
	// JDBC variables for opening and managing connection
	private Connection getConnection() throws SQLException, IOException {
		return DriverManager.getConnection(url, username, password);
	}
	
	// Метод для виконання SQL-запиту
	public boolean QueryExecute(String query) {
		try (Connection connection = getConnection();
		     Statement statement = connection.createStatement()) {
			
			boolean hasResultSet = statement.execute(query);
			
			if (hasResultSet) {
				try (ResultSet rs = statement.getResultSet()) {
					while (rs.next()) {
						for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
							System.out.print(rs.getString(i) + "\t");
						}
						System.out.println();
					}
				}
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
