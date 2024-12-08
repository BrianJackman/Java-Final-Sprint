package com.ecommerce.cli;

import com.ecommerce.model.User;
import com.ecommerce.model.Buyer;
import com.ecommerce.model.Seller;
import com.ecommerce.model.Admin;
import com.ecommerce.model.Product;
import com.ecommerce.service.UserService;
import com.ecommerce.service.ProductService;
import com.ecommerce.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private UserService userService;
    private ProductService productService;

    public Menu(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    public void displayMenu() {
        System.out.println("Displaying main menu..."); // Debugging statement
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
        System.out.println("Registering user..."); // Debugging statement
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
        System.out.println("Logging in user..."); // Debugging statement
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
        System.out.println("Displaying role-based menu..."); // Debugging statement
        Scanner scanner = new Scanner(System.in);
        if (user.getRole().equalsIgnoreCase("buyer")) {
            displayBuyerMenu(scanner, user);
        } else if (user.getRole().equalsIgnoreCase("seller")) {
            displaySellerMenu(scanner, user);
        } else if (user.getRole().equalsIgnoreCase("admin")) {
            displayAdminMenu(scanner);
        }
    }

    private void displayBuyerMenu(Scanner scanner, User user) {
        while (true) {
            System.out.println("1. Browse products");
            System.out.println("2. Search for a product");
            System.out.println("3. View product info");
            System.out.println("4. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    browseProducts();
                    break;
                case 2:
                    searchProduct(scanner);
                    break;
                case 3:
                    viewProductInfo(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displaySellerMenu(Scanner scanner, User user) {
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
                    addProduct(scanner, user);
                    break;
                case 2:
                    updateProduct(scanner, user);
                    break;
                case 3:
                    deleteProduct(scanner, user);
                    break;
                case 4:
                    viewMyProducts(user);
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
                    viewAllUsers();
                    break;
                case 2:
                    deleteUser(scanner);
                    break;
                case 3:
                    viewAllProducts();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void browseProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            for (Product product : products) {
                System.out.println(product);
            }
        } catch (SQLException e) {
            System.out.println("Error browsing products: " + e.getMessage());
        }
    }

    private void searchProduct(Scanner scanner) {
        System.out.println("Enter product name to search:");
        String productName = scanner.nextLine();
        try {
            List<Product> products = productService.searchProductsByName(productName);
            for (Product product : products) {
                System.out.println(product);
            }
        } catch (SQLException e) {
            System.out.println("Error searching for product: " + e.getMessage());
        }
    }

    private void viewProductInfo(Scanner scanner) {
        System.out.println("Enter product ID to view info:");
        int productId = getIntInput(scanner);
        if (productId == -1) return;
        try {
            Product product = productService.getProductById(productId);
            if (product != null) {
                System.out.println(product);
            } else {
                System.out.println("Product not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error viewing product info: " + e.getMessage());
        }
    }

    private void addProduct(Scanner scanner, User user) {
        System.out.println("Enter product name:");
        String name = scanner.nextLine();
        System.out.println("Enter product price:");
        double price = scanner.nextDouble();
        System.out.println("Enter product quantity:");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Product product = new Product(name, price, quantity, user.getUser_id());

        try {
            productService.addProduct(product);
            System.out.println("Product added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }

    private void updateProduct(Scanner scanner, User user) {
        System.out.println("Enter product ID to update:");
        int productId = getIntInput(scanner);
        if (productId == -1) return;
        System.out.println("Enter new product name:");
        String name = scanner.nextLine();
        System.out.println("Enter new product price:");
        double price = scanner.nextDouble();
        System.out.println("Enter new product quantity:");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Product product = new Product(productId, name, price, quantity, user.getUser_id());

        try {
            productService.updateProduct(product);
            System.out.println("Product updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating product: " + e.getMessage());
        }
    }

    private void deleteProduct(Scanner scanner, User user) {
        System.out.println("Enter product ID to delete:");
        int productId = getIntInput(scanner);
        if (productId == -1) return;

        try {
            productService.deleteProduct(productId);
            System.out.println("Product deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting product: " + e.getMessage());
        }
    }

    private void viewMyProducts(User user) {
        try {
            List<Product> products = productService.getProductsBySeller(user.getUser_id());
            for (Product product : products) {
                System.out.println(product);
            }
        } catch (SQLException e) {
            System.out.println("Error viewing your products: " + e.getMessage());
        }
    }

    private void viewAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            for (User user : users) {
                System.out.println(user);
            }
        } catch (SQLException e) {
            System.out.println("Error viewing users: " + e.getMessage());
        }
    }

    private void deleteUser(Scanner scanner) {
        System.out.println("Enter username of the user to delete:");
        String username = scanner.nextLine();
        try {
            boolean deleted = userService.deleteUser(username);
            if (deleted) {
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }

    private void viewAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            for (Product product : products) {
                System.out.println(product);
            }
        } catch (SQLException e) {
            System.out.println("Error viewing products: " + e.getMessage());
        }
    }

    private int getIntInput(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.nextLine(); // Consume the invalid input
            return -1;
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("Connecting to the database..."); // Debugging statement
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                System.out.println("Connected to the database!");
                UserService userService = new UserService(connection);
                ProductService productService = new ProductService(connection);
                Menu menu = new Menu(userService, productService);
                menu.displayMenu();
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }
}