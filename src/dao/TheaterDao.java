package dao;

import util.DBConnection;
import dto.Show;
import dto.Theater;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TheaterDao {

    public static void insertTheater(Theater theater) {
        String sql = "INSERT INTO theaters (name, location) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, theater.getName());
            pstmt.setString(2, theater.getLocation());

            pstmt.executeUpdate();
            System.out.println("✅ Theater inserted.");

        } catch (Exception e) {
            System.out.println("❌ Error inserting theater: " + e.getMessage());
        }
    }

    public static List<Theater> getAllTheaters() {
        List<Theater> theaters = new ArrayList<>();
        String sql = "SELECT * FROM theaters";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                theaters.add(new Theater(
                        rs.getInt("theater_id"),
                        rs.getString("name"),
                        rs.getString("location")
                ));
            }

        } catch (Exception e) {
            System.out.println("❌ Error fetching theaters: " + e.getMessage());
        }

        return theaters;
    }
    
    public List<Show> searchShowsByMovieTitle(String movieTitle) {
        List<Show> shows = new ArrayList<>();

        String sql = "SELECT s.show_id, s.movie_id, s.screen_id, s.show_time, s.price,"+
                  " m.title AS movie_title, t.name AS theater_name"+
           " FROM shows s"+
            "JOIN movies m ON s.movie_id = m.movie_id"+
            "JOIN screens sc ON s.screen_id = sc.screen_id"+
           " JOIN theaters t ON sc.theater_id = t.theater_id"+
           " WHERE m.title LIKE ?";
            

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + movieTitle + "%");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int showId = rs.getInt("show_id");
                int movieId = rs.getInt("movie_id");
                int screenId = rs.getInt("screen_id");
                LocalDateTime showTime = rs.getTimestamp("show_time").toLocalDateTime();
                double price = rs.getDouble("price");

                Show show = new Show(showId, movieId, screenId, showTime, price);

                // Optional: Add title and theater name to Show if your Show DTO supports it
                show.setMovieTitle(rs.getString("movie_title"));     // Add this method in Show DTO
                show.setTheaterName(rs.getString("theater_name"));   // Add this method in Show DTO

                shows.add(show);
            }

        } catch (Exception e) {
            System.out.println("❌ Error searching shows: " + e.getMessage());
        }

        return shows;
    }

}
