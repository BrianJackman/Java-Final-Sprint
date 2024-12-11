package com.ecommerce.service;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Handles product-related operations such as adding, updating, and deleting products.
 */
public class ProductService {
    private ProductDAO productDAO;

    /**
     * Constructs a new ProductService.
     *
     * @param connection the database connection
     */
    public ProductService(Connection connection) {
        this.productDAO = new ProductDAO(connection);
    }

    /**
     * Adds a new product.
     *
     * @param product the product to add
     * @throws SQLException if a database access error occurs
     */
    public void addProduct(Product product) throws SQLException {
        productDAO.addProduct(product);
    }

    /**
     * Updates an existing product.
     *
     * @param product the product to update
     * @throws SQLException if a database access error occurs
     */
    public void updateProduct(Product product) throws SQLException {
        productDAO.updateProduct(product);
    }

    /**
     * Deletes a product by ID.
     *
     * @param productId the product ID
     * @throws SQLException if a database access error occurs
     */
    public void deleteProduct(int productId) throws SQLException {
        productDAO.deleteProduct(productId);
    }

    /**
     * Retrieves a product by ID.
     *
     * @param productId the product ID
     * @return the product, or null if not found
     * @throws SQLException if a database access error occurs
     */
    public Product getProductById(int productId) throws SQLException {
        return productDAO.getProductById(productId);
    }

    /**
     * Retrieves all products.
     *
     * @return a list of all products
     * @throws SQLException if a database access error occurs
     */
    public List<Product> getAllProducts() throws SQLException {
        return productDAO.getAllProducts();
    }

    /**
     * Searches for products by name.
     *
     * @param productName the product name
     * @return a list of products matching the name
     * @throws SQLException if a database access error occurs
     */
    public List<Product> searchProductsByName(String productName) throws SQLException {
        return productDAO.searchProductsByName(productName);
    }

    /**
     * Retrieves products by seller ID.
     *
     * @param sellerId the seller ID
     * @return a list of products by the seller
     * @throws SQLException if a database access error occurs
     */
    public List<Product> getProductsBySeller(int sellerId) throws SQLException {
        return productDAO.getProductsBySeller(sellerId);
    }
}