package service;

import dao.BookingDao;
import dao.MovieDao;
import dao.ShowDao;
import dao.TheaterDao;
import dto.Movie;
import dto.Show;
import dto.Theater;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AdminService {
    static Scanner sc = new Scanner(System.in);

    public static void addMovie() {
        System.out.println("\n--- Add New Movie ---");

        System.out.print("Enter movie name: ");
        String name = sc.nextLine();

        System.out.print("Enter genre: ");
        String genre = sc.nextLine();

        System.out.print("Enter duration (in minutes): ");
        int duration = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Enter language: ");
        String language = sc.nextLine();

        Movie movie = new Movie(name, genre, duration, language);
        boolean result = MovieDao.insertMovie(movie);

        if (result) {
            System.out.println("✅ Movie added successfully.");
        } else {
            System.out.println("❌ Failed to add movie.");
        }
    }

    public static void updateMovie() {
        System.out.println("\n--- Update Movie ---");

        System.out.print("Enter movie ID to update: ");
        int movieId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter new movie name: ");
        String name = sc.nextLine();

        System.out.print("Enter new genre: ");
        String genre = sc.nextLine();

        System.out.print("Enter new duration (minutes): ");
        int duration = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter new language: ");
        String language = sc.nextLine();

        Movie movie = new Movie(name, genre, duration, language);
        boolean updated = MovieDao.updateMovie(movieId, movie);

        if (updated) {
            System.out.println("✅ Movie updated successfully.");
        } else {
            System.out.println("❌ Failed to update movie.");
        }
    }

    public static void deleteMovie() {
        System.out.println("\n--- Delete Movie ---");
        System.out.print("Enter movie ID to delete: ");
        int movieId = sc.nextInt();
        sc.nextLine();

        boolean deleted = MovieDao.deleteMovie(movieId);

        if (deleted) {
            System.out.println("✅ Movie deleted successfully.");
        } else {
            System.out.println("❌ Failed to delete movie.");
        }
    }

    public static void viewAllMovies() {
        System.out.println("\n--- All Movies ---");
        MovieDao.viewAllMovies();
    }

    public static void addTheater() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- Add Theater ---");
        System.out.print("Enter theater name: ");
        String name = sc.nextLine();
        System.out.print("Enter location: ");
        String location = sc.nextLine();

        Theater theater = new Theater(0, name, location); // 0 as ID will be auto-generated
        TheaterDao.insertTheater(theater);
    }
    public static void addShow() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- Add Show ---");

        System.out.print("Enter movie ID: ");
        int movieId = Integer.parseInt(sc.nextLine());

        System.out.print("Enter screen ID: ");
        int screenId = Integer.parseInt(sc.nextLine());

        System.out.print("Enter show time (yyyy-MM-dd HH:mm): ");
        String showTimeStr = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime showTime = LocalDateTime.parse(showTimeStr, formatter);

        System.out.print("Enter price: ");
        double price = Double.parseDouble(sc.nextLine());

        Show show = new Show(0, movieId, screenId, showTime, price);
        ShowDao.insertShow(show);  // your existing insertShow method
    }

    public static void viewAllBookings() {
        System.out.println("\n--- All Bookings ---");
        BookingDao.viewAllBookings();
    }
}
