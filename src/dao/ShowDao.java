package dao;

import util.DBConnection;
import dto.Show;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ShowDao {

    // Insert a new show
    public static void insertShow(Show show) {
        String sql = "INSERT INTO shows (movie_id, screen_id, show_time, price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, show.getMovieId());
            pstmt.setInt(2, show.getScreenId());
            pstmt.setTimestamp(3, Timestamp.valueOf(show.getShowTime()));
            pstmt.setDouble(4, show.getPrice());

            pstmt.executeUpdate();
            System.out.println("✅ Show inserted.");

        } catch (Exception e) {
            System.out.println("❌ Error inserting show: " + e.getMessage());
        }
    }

    // Get all shows
    public static List<Show> getAllShows() {
        List<Show> shows = new ArrayList<>();
        String sql = "SELECT * FROM shows";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int showId = rs.getInt("show_id");
                int movieId = rs.getInt("movie_id");
                int screenId = rs.getInt("screen_id");
                LocalDateTime showTime = rs.getTimestamp("show_time").toLocalDateTime();
                double price = rs.getDouble("price");

                shows.add(new Show(showId, movieId, screenId, showTime, price));
            }

        } catch (Exception e) {
            System.out.println("❌ Error fetching shows: " + e.getMessage());
        }

        return shows;
    }

    // Get a show by ID
    public static Show getShowById(int showId) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM shows WHERE show_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, showId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int movieId = rs.getInt("movie_id");
                int screenId = rs.getInt("screen_id");
                Timestamp timestamp = rs.getTimestamp("show_time");
                LocalDateTime showTime = timestamp.toLocalDateTime();
                double price = rs.getDouble("price");

                return new Show(showId, movieId, screenId, showTime, price);
            }
        } catch (Exception e) {
            System.out.println("❌ Error getting show: " + e.getMessage());
        }
        return null;
    }

    // ✅ Search shows by movie title (new method)
    public static  List<Show> searchShowsByMovieTitle(String movieTitle) {
        List<Show> shows = new ArrayList<>();

        String sql = "SELECT s.show_id, s.movie_id, s.screen_id, s.show_time, s.price,"+
                   "m.title AS movie_title, t.name AS theater_name"+
            "FROM shows s"+
            "JOIN movies m ON s.movie_id = m.movie_id"+
            "JOIN screens sc ON s.screen_id = sc.screen_id"+
            "JOIN theaters t ON sc.theater_id = t.theater_id"+
            "WHERE m.title LIKE ?";

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

                // Optional: Add movie title and theater name
                show.setMovieTitle(rs.getString("movie_title"));       // requires Show.java update
                show.setTheaterName(rs.getString("theater_name"));     // requires Show.java update

                shows.add(show);
            }

        } catch (Exception e) {
            System.out.println("❌ Error searching shows: " + e.getMessage());
        }

        return shows;
    }
}
