package dao;

import java.sql.Connection;
import java.sql.Statement;
import util.DBConnection;

public class TableCreator {

    public static void createAllTables() {
        try (Connection conn = DBConnection.getConnection(); 
        Statement stmt = conn.createStatement()) {

            String userTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "user_id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "username VARCHAR(50) UNIQUE NOT NULL, " +
                    "password VARCHAR(255) NOT NULL, " +
                    "email VARCHAR(100) NOT NULL" +
                    ");";

            String movieTable = "CREATE TABLE IF NOT EXISTS movies (" +
                    "movie_id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "title VARCHAR(100) NOT NULL, " +
                    "genre VARCHAR(50), " +
                    "duration INT, " +
                    "rating VARCHAR(10)" +
                    ");";

            String theaterTable = "CREATE TABLE IF NOT EXISTS theaters (" +
                    "theater_id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "location VARCHAR(100)" +
                    ");";

            String screenTable = "CREATE TABLE IF NOT EXISTS screens (" +
                    "screen_id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "theater_id INT, " +
                    "screen_number INT, " +
                    "capacity INT, " +
                    "FOREIGN KEY (theater_id) REFERENCES theaters(theater_id)" +
                    ");";

            String showTable = "CREATE TABLE IF NOT EXISTS shows (" +
                    "show_id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "movie_id INT, " +
                    "screen_id INT, " +
                    "show_time DATETIME, " +
                    "price DECIMAL(6,2), " +
                    "FOREIGN KEY (movie_id) REFERENCES movies(movie_id), " +
                    "FOREIGN KEY (screen_id) REFERENCES screens(screen_id)" +
                    ");";
            
            String bookingTable = "CREATE TABLE IF NOT EXISTS bookings (" +
                    "booking_id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "user_id INT, " +
                    "show_id INT, " +
                    "seats_booked INT, " +
                    "booking_time DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY (user_id) REFERENCES users(user_id), " +
                    "FOREIGN KEY (show_id) REFERENCES shows(show_id)" +
                    ");";

            stmt.execute(userTable);
            stmt.execute(movieTable);
            stmt.execute(theaterTable);
            stmt.execute(screenTable);
            stmt.execute(showTable);
            stmt.execute(bookingTable);

            System.out.println("✅ Database setup successful! Tables for users, movies, theaters, shows, and bookings are ready.");


        } catch (Exception e) {
            System.out.println("❌ Error creating tables: " + e.getMessage());
        }
    }
}
