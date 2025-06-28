package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import util.DBConnection;
import dto.Booking;

public class BookingDao{
	public static boolean bookTicket(Booking booking) {
		String insertBooking = "INSERT INTO bookings (user_id, show_id, seats_booked) VALUES (?, ?, ?)";

		 try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(insertBooking);
				){
			 
			 	pstmt.setInt(1, booking.getUserId());
	            pstmt.setInt(2, booking.getShowId());
	            pstmt.setInt(3, booking.getSeatsBooked());
	            int rows = pstmt.executeUpdate();
	            return rows > 0;
			 
		 }catch (Exception e) {
			 System.out.println("❌ Booking failed: " + e.getMessage());
	            return false;
		}
	}
	
	 public static List<Booking> getBookingsByUserId(int userId) {
	        List<Booking> bookings = new ArrayList<>();

	        String sql = "SELECT b.booking_id, s.movie_name, s.show_time, b.seats_booked, b.booking_time"+
	            "FROM bookings b"+
	            "JOIN shows s ON b.show_id = s.show_id"+
	            "WHERE b.user_id = ?"+
	            "ORDER BY b.booking_time DESC";

	        try (Connection conn = DBConnection.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {

	            pstmt.setInt(1, userId);
	            ResultSet rs = pstmt.executeQuery();

	            while (rs.next()) {
	                int bookingId = rs.getInt("booking_id");
	                String movie = rs.getString("movie_name");
	                LocalDateTime showTime = rs.getTimestamp("show_time").toLocalDateTime();
	                int seats = rs.getInt("seats_booked");
	                LocalDateTime bookingTime = rs.getTimestamp("booking_time").toLocalDateTime();

	                bookings.add(new Booking(bookingId, movie, showTime, seats, bookingTime));
	            }

	        } catch (Exception e) {
	            System.out.println("Error fetching booking history: " + e.getMessage());
	        }

	        return bookings;
	    }
	 
	 public static void viewAllBookings() {
		    String sql = "SELECT b.booking_id, u.username, m.title, s.show_time, b.seats_booked, b.booking_time " +
		                 "FROM bookings b " +
		                 "JOIN users u ON b.user_id = u.user_id " +
		                 "JOIN shows s ON b.show_id = s.show_id " +
		                 "JOIN movies m ON s.movie_id = m.movie_id " +
		                 "ORDER BY b.booking_time DESC";

		    try (Connection conn = DBConnection.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(sql);
		         ResultSet rs = pstmt.executeQuery()) {

		        System.out.println("\n--- All Bookings ---");
		        System.out.printf("%-10s %-15s %-20s %-20s %-10s %-20s%n",
		                "BookingID", "Username", "Movie", "Show Time", "Seats", "Booking Time");
		        System.out.println("------------------------------------------------------------------------------------");

		        while (rs.next()) {
		            int bookingId = rs.getInt("booking_id");
		            String username = rs.getString("username");
		            String movieTitle = rs.getString("title");
		            String showTime = rs.getTimestamp("show_time").toString();
		            int seats = rs.getInt("seats_booked");
		            String bookingTime = rs.getTimestamp("booking_time").toString();

		            System.out.printf("%-10d %-15s %-20s %-20s %-10d %-20s%n",
		                    bookingId, username, movieTitle, showTime, seats, bookingTime);
		        }
		    } catch (Exception e) {
		        System.out.println("❌ Error viewing bookings: " + e.getMessage());
		    }
		}


}
