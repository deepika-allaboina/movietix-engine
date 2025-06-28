package service;

import dao.BookingDao;
import dao.MovieDao;
import dao.ShowDao;
import dao.UserDao;
import dto.Booking;
import dto.Movie;
import dto.Show;
import dto.User;

import java.util.List;

public class UserService {

    public boolean registerUser(String username, String password, String email) {
        return UserDao.insertUser(username, password, email);
    }

    public User loginUser(String username, String password) {
        return UserDao.getUserByUsernameAndPassword(username, password);
    }

    public static void viewAllMovies() {
        System.out.println("\n--- All Movies ---");
        MovieDao.viewAllMovies();
    }

    public static void viewShowsByMovieTitle(String keyword) {
        List<Show> shows = ShowDao.searchShowsByMovieTitle(keyword); // ✅ fixed from MovieDao
        if (shows.isEmpty()) {
            System.out.println("❌ No shows found for that movie.");
        } else {
            System.out.println("📅 Shows Found:");
            for (Show show : shows) {
                System.out.println(" - Show ID: " + show.getShowId()
                        + ", Movie ID: " + show.getMovieId()
                        + ", Screen ID: " + show.getScreenId()
                        + ", Time: " + show.getShowTime()
                        + ", Price: Rs." + show.getPrice());
            }
        }
    }

    public void bookTickets(int userId, int showId, int seats) {
    	
        boolean success = BookingDao.bookTicket(new Booking(userId, showId, seats));
        if (success) {
            System.out.println("✅ Booking successful!");
        } else {
            System.out.println("❌ Booking failed. Please check show ID and seat availability.");
        }
    }

    public void viewBookings(int userId) {
        BookingDao.getBookingsByUserId(userId);
    }
}
