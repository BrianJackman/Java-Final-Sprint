package com.ecommerce.cli;

import com.ecommerce.model.User;
import com.ecommerce.model.Buyer;
import com.ecommerce.model.Seller;
import com.ecommerce.model.Admin;
import com.ecommerce.service.UserService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private UserService userService;

    public Menu(UserService userService) {
        this.userService = userService;
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    loginUser(scanner);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void registerUser(Scanner scanner) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Enter role (buyer, seller, admin):");
        String role = scanner.nextLine();

        User user;
        int role_id = 0; // Assign appropriate role_id based on your logic
        switch (role.toLowerCase()) {
            case "buyer":
                user = new Buyer(0, username, password, email, role, role_id);
                break;
            case "seller":
                user = new Seller(0, username, password, email, role, role_id);
                break;
            case "admin":
                user = new Admin(0, username, password, email, role, role_id);
                break;
            default:
                System.out.println("Invalid role. Please try again.");
                return;
        }

        try {
            userService.registerUser(user);
            System.out.println("User registered successfully.");
        } catch (SQLException e) {
            System.out.println("Error registering user: " + e.getMessage());
        } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }

    private void loginUser(Scanner scanner) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        try {
            boolean authenticated = userService.authenticateUser(username, password);
            if (authenticated) {
                System.out.println("Login successful.");
                // Display role-based menu
                User user = userService.getUserByUsername(username);
                displayRoleBasedMenu(user);
            } else {
                System.out.println("Invalid username or password.");
            }
        } catch (SQLException e) {
            System.out.println("Error logging in: " + e.getMessage());
        }
    }

    private void displayRoleBasedMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        if (user.getRole().equalsIgnoreCase("buyer")) {
            displayBuyerMenu(scanner);
        } else if (user.getRole().equalsIgnoreCase("seller")) {
            displaySellerMenu(scanner);
        } else if (user.getRole().equalsIgnoreCase("admin")) {
            displayAdminMenu(scanner);
        }
    }

    private void displayBuyerMenu(Scanner scanner) {
        while (true) {
            System.out.println("1. Browse products");
            System.out.println("2. Search for a product");
            System.out.println("3. View product info");
            System.out.println("4. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Browse products logic
                    break;
                case 2:
                    // Search for a product logic
                    break;
                case 3:
                    // View product info logic
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displaySellerMenu(Scanner scanner) {
        while (true) {
            System.out.println("1. Add product");
            System.out.println("2. Update product");
            System.out.println("3. Delete product");
            System.out.println("4. View my products");
            System.out.println("5. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add product logic
                    break;
                case 2:
                    // Update product logic
                    break;
                case 3:
                    // Delete product logic
                    break;
                case 4:
                    // View my products logic
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayAdminMenu(Scanner scanner) {
        while (true) {
            System.out.println("1. View all users");
            System.out.println("2. Delete user");
            System.out.println("3. View all products");
            System.out.println("4. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // View all users logic
                    break;
                case 2:
                    // Delete user logic
                    break;
                case 3:
                    // View all products logic
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce", "yourusername", "yourpassword");
            UserService userService = new UserService(connection);
            Menu menu = new Menu(userService);
            menu.displayMenu();
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }
}