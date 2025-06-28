package controller;

import service.AdminService;

import java.util.Scanner;

public class AdminController {
    static Scanner sc = new Scanner(System.in);

    public static void adminPanel() {
        while (true) {
            System.out.println("\n================== 🎬 ADMIN PANEL ==================");
            System.out.println("1. Add Movie");
            System.out.println("2. Update Movie");
            System.out.println("3. Delete Movie");
            System.out.println("4. View All Movies");
            System.out.println("5. Add Theater");
            System.out.println("6. Add Show");
            System.out.println("7. View All Bookings");
            System.out.println("8. Exit Admin Panel");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 : AdminService.addMovie(); break;
                case 2 : AdminService.updateMovie(); break;
                case 3 : AdminService.deleteMovie(); break;
                case 4 : AdminService.viewAllMovies(); break;
                case 5 : AdminService.addTheater(); break;
                case 6 : AdminService.addShow(); break;
                case 7 : AdminService.viewAllBookings(); break;
                case 8 : {
                    System.out.println("Exiting Admin Panel...");
                    return;
                }
                default : System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }
}
