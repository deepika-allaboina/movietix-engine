package dao;

import dto.Movie;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MovieDao {

    // ✅ INSERT MOVIE
    public static boolean insertMovie(Movie movie) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO movies (title, genre, duration, rating) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getGenre());
            ps.setInt(3, movie.getDuration());
            ps.setString(4, movie.getRating());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("❌ Error inserting movie: " + e.getMessage());
            return false;
        }
    }

    // ✅ UPDATE MOVIE
    public static boolean updateMovie(int movieId, Movie movie) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE movies SET title = ?, genre = ?, duration = ?, rating = ? WHERE movie_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getGenre());
            ps.setInt(3, movie.getDuration());
            ps.setString(4, movie.getRating());
            ps.setInt(5, movieId);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("❌ Error updating movie: " + e.getMessage());
            return false;
        }
    }

    // ✅ DELETE MOVIE
    public static boolean deleteMovie(int movieId) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM movies WHERE movie_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, movieId);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("❌ Error deleting movie: " + e.getMessage());
            return false;
        }
    }

    // ✅ VIEW ALL MOVIES
    public static void viewAllMovies() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM movies";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\n🎬 Movie List:");
            System.out.println("--------------------------------------------------------");
            System.out.printf("%-5s %-20s %-10s %-10s %-10s%n", "ID", "Title", "Genre", "Duration", "Rating");
            System.out.println("--------------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("movie_id");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                int duration = rs.getInt("duration");
                String rating = rs.getString("rating");

                System.out.printf("%-5d %-20s %-10s %-10d %-10s%n", id, title, genre, duration, rating);
            }

            System.out.println("--------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("❌ Error fetching movies: " + e.getMessage());
        }
    }
    
    public static void searchMovieByTitle(String keyword) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM movies WHERE title LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            boolean found = false;
            System.out.println("\n🔍 Search Results:");
            System.out.println("--------------------------------------------------------");
            System.out.printf("%-5s %-20s %-10s %-10s %-10s%n", "ID", "Title", "Genre", "Duration", "Rating");
            System.out.println("--------------------------------------------------------");

            while (rs.next()) {
                found = true;
                int id = rs.getInt("movie_id");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                int duration = rs.getInt("duration");
                String rating = rs.getString("rating");

                System.out.printf("%-5d %-20s %-10s %-10d %-10s%n", id, title, genre, duration, rating);
            }

            if (!found) {
                System.out.println("❌ No movies found matching: " + keyword);
            }

            System.out.println("--------------------------------------------------------");

        } catch (Exception e) {
            System.out.println("❌ Error searching movie: " + e.getMessage());
        }
    }

}
