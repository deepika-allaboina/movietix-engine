package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {
	 private static final String DB_NAME = "movie_booking_system";
	    private static final String URL_NO_DB = "jdbc:mysql://localhost:3306/";
	    private static final String URL_WITH_DB = "jdbc:mysql://localhost:3306/" + DB_NAME;
	    private static final String USER = "root";
	    private static final String PASSWORD = "your_password"; // Replace with your MySQL password

	    static {
	        try {
	            // Step 1: Connect without DB and create it if not exists
	            Connection conn = DriverManager.getConnection(URL_NO_DB, USER, PASSWORD);
	            Statement stmt = conn.createStatement();
	            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
	            stmt.close();
	            conn.close();
	            System.out.println("✅ Database ready: " + DB_NAME);
	        } catch (Exception e) {
	            System.out.println("❌ Error creating database: " + e.getMessage());
	        }
	    }

	    public static Connection getConnection() {
	        try {
	            return DriverManager.getConnection(URL_WITH_DB, USER, PASSWORD);
	        } catch (Exception e) {
	            System.out.println("❌ Error connecting to DB: " + e.getMessage());
	            return null;
	        }
	    }
}
