package com.ecommerce.dao;

import com.ecommerce.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for product-related database operations.
 */
public class ProductDAO {
    private Connection connection;

    /**
     * Constructs a new ProductDAO.
     *
     * @param connection the database connection
     */
    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Adds a new product to the database.
     *
     * @param product the product to add
     * @throws SQLException if a database access error occurs
     */
    public void addProduct(Product product) throws SQLException {
        String query = "INSERT INTO products (name, price, quantity, seller_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());
            stmt.setInt(4, product.getSellerId());
            stmt.executeUpdate();
        }
    }

    /**
     * Updates an existing product in the database.
     *
     * @param product the product to update
     * @throws SQLException if a database access error occurs
     */
    public void updateProduct(Product product) throws SQLException {
        String query = "UPDATE products SET name = ?, price = ?, quantity = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());
            stmt.setInt(4, product.getId());
            stmt.executeUpdate();
        }
    }

    /**
     * Deletes a product by ID.
     *
     * @param productId the product ID
     * @throws SQLException if a database access error occurs
     */
    public void deleteProduct(int productId) throws SQLException {
        String query = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        }
    }

    /**
     * Retrieves a product by ID.
     *
     * @param productId the product ID
     * @return the product, or null if not found
     * @throws SQLException if a database access error occurs
     */
    public Product getProductById(int productId) throws SQLException {
        String query = "SELECT * FROM products WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getInt("seller_id")
                    );
                }
            }
        }
        return null;
    }

    /**
     * Retrieves all products from the database.
     *
     * @return a list of all products
     * @throws SQLException if a database access error occurs
     */
    public List<Product> getAllProducts() throws SQLException {
        String query = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                products.add(new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("quantity"),
                    rs.getInt("seller_id")
                ));
            }
        }
        return products;
    }

    /**
     * Searches for products by name.
     *
     * @param productName the product name
     * @return a list of products matching the name
     * @throws SQLException if a database access error occurs
     */
    public List<Product> searchProductsByName(String productName) throws SQLException {
        String query = "SELECT * FROM products WHERE name ILIKE ?";
        List<Product> products = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, "%" + productName + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getInt("seller_id")
                    ));
                }
            }
        }
        return products;
    }

    /**
     * Retrieves products by seller ID.
     *
     * @param sellerId the seller ID
     * @return a list of products by the seller
     * @throws SQLException if a database access error occurs
     */
    public List<Product> getProductsBySeller(int sellerId) throws SQLException {
        String query = "SELECT * FROM products WHERE seller_id = ?";
        List<Product> products = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, sellerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getInt("seller_id")
                    ));
                }
            }
        }
        return products;
    }
}