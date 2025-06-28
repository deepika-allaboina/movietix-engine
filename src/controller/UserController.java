package controller;

import service.UserService;
import dto.User;

import java.util.Scanner;

public class UserController {
    private static final Scanner sc = new Scanner(System.in);
    private static final UserService userService = new UserService();

    // Register new user
    public static void registerUser() {
        System.out.println("\n👤 Register New User");
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        boolean success = userService.registerUser(username, password, email);
        if (success) {
            System.out.println("✅ Registration successful. Please log in.");
        } else {
            System.out.println("❌ Registration failed. Username might already exist.");
        }
    }

    // Login existing user
    public static void loginUser() {
        System.out.println("\n🔐 User Login");
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        User user = userService.loginUser(username, password);
        if (user != null) {
            System.out.println("✅ Login successful! Welcome, " + user.getUsername() + ".");
            showUserMenu(user); // Only access if login is successful
        } else {
            System.out.println("❌ Invalid credentials. Access denied.");
        }
    }

    // After login user menu
    public static void showUserMenu(User loggedInUser) {
        while (true) {
            System.out.println("\n========= 🎟️ USER MENU =========");
            System.out.println("1. View All Movies");
            System.out.println("2. View Shows for a Movie");
            System.out.println("3. Book Tickets");
            System.out.println("4. View My Bookings");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    UserService.viewAllMovies();
                    break;

                case 2:
                    System.out.print("Enter Movie Title Keyword: ");
                    String keyword = sc.nextLine();
                    UserService.viewShowsByMovieTitle(keyword);
                    break;

                case 3:
                    System.out.print("Enter Show ID to book: ");
                    int showId = sc.nextInt();
                    System.out.print("Enter number of seats: ");
                    int seats = sc.nextInt();
                    sc.nextLine();
                    userService.bookTickets(loggedInUser.getUserId(), showId, seats);
                    break;

                case 4:
                    userService.viewBookings(loggedInUser.getUserId());
                    break;

                case 5:
                    System.out.println("🚪 Logged out.");
                    return;

                default:
                    System.out.println("❌ Invalid option. Please try again.");
            }
        }
    }
}
