package controller;

import dao.TableCreator;

import java.util.Scanner;

public class Main {
    static final Scanner sc = new Scanner(System.in);

    static {
        TableCreator.createAllTables();
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n==============================================");
            System.out.println("       🎬 MOVIE TICKET BOOKING SYSTEM        ");
            System.out.println("==============================================");
            System.out.println("1. Register as User");
            System.out.println("2. Login as User");
            System.out.println("3. Admin Panel");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 : UserController.registerUser(); break;
                case 2 : UserController.loginUser(); break;
                case 3 : AdminController.adminPanel(); break;
                case 4 : {
                    System.out.println("👋 Exiting. Goodbye!");
                    return;
                }
                default : System.out.println("❌ Invalid input. Try again."); 
            }
        }
    }
}
