package service;

import dao.BookingDao;
import dao.ShowDao;
import dto.Booking;
import dto.Show;
import dto.User;
import dao.UserDao;

import java.util.List;
import java.util.Scanner;

public class UserOperations {
    static final Scanner sc = new Scanner(System.in);
    static UserDao userDao = new UserDao();
    static ShowDao showDao = new ShowDao();
    static BookingDao bookingDao = new BookingDao();

    public static void registerUser() {
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();

        boolean success = UserDao.insertUser(username, password, email);
        if (success) {
            System.out.println("✅ Registration successful!");
        } else {
            System.out.println("❌ Registration failed. Username may already exist or there was a problem.");
        }
    }


    public static void loginUser() {
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        User user=UserDao.getUserByUsernameAndPassword(username, password);
        int userId = user.getUserId();
        if (userId == -1) {
            System.out.println("❌ Invalid credentials.");
            return;
        }

        System.out.println("✅ Login successful!");
        userMenu(userId);
    }

    private static void userMenu(int userId) {
        while (true) {
            System.out.println("\n---------- USER MENU ----------");
            System.out.println("1. View Available Shows");
            System.out.println("2. Book a Ticket");
            System.out.println("3. View My Bookings");
            System.out.println("4. Logout");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 : viewShows(); break;
                case 2 : bookTicket(userId);break;
                case 3 : viewBookings(userId);break;
                case 4 : {
                    System.out.println("🔒 Logged out.");
                    return;
                }
                default : System.out.println("❌ Invalid option.");
            }
        }
    }

    private static void viewShows() {
        List<Show> shows = showDao.getAllShows();
        if (shows.isEmpty()) {
            System.out.println("No shows available.");
            return;
        }
        for (Show show : shows) {
            System.out.printf("Show ID: %d | Movie ID: %d | Screen ID: %d | Time: %s | Price: ₹%.2f\n",
                    show.getShowId(), show.getMovieId(), show.getScreenId(),
                    show.getShowTime(), show.getPrice());
        }
    }

    private static void bookTicket(int userId) {
        viewShows();
        System.out.print("Enter Show ID to book: ");
        int showId = sc.nextInt();
        System.out.print("Enter number of seats: ");
        int seats = sc.nextInt();
        sc.nextLine();

        Booking booking = new Booking(userId, showId, seats);
        boolean success = bookingDao.bookTicket(booking);
        System.out.println(success ? "✅ Booking confirmed!" : "❌ Booking failed.");
    }

    private static void viewBookings(int userId) {
        List<Booking> bookings = bookingDao.getBookingsByUserId(userId);
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        for (Booking b : bookings) {
            System.out.printf("Booking ID: %d | Show ID: %d | Seats: %d | Time: %s\n",
                    b.getBookingId(), b.getShowId(), b.getSeatsBooked(), b.getBookingTime());
        }
    }
}
