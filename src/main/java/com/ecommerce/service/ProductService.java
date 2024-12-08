package com.ecommerce.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.Product;

public class ProductService {
    private final ProductDAO productDAO;

    public ProductService(Connection connection) {
        this.productDAO = new ProductDAO(connection);
    }

    public boolean addProduct(Product product) {
        try {
            return productDAO.addProduct(product);
        } catch (SQLException e) {
            System.out.println("Error adding product: " + e.getMessage());
            return false;
        }
    }

    public List<Product> getProductsBySeller(int sellerId) {
        try {
            return productDAO.getProductsBySeller(sellerId);
        } catch (SQLException e) {
            System.out.println("Error fetching products: " + e.getMessage());
            return null;
        }
    }

    public boolean updateProduct(Product product) {
        try {
            return productDAO.updateProduct(product);
        } catch (SQLException e) {
            System.out.println("Error updating product: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteProduct(int productId) {
        try {
            return productDAO.deleteProduct(productId);
        } catch (SQLException e) {
            System.out.println("Error deleting product: " + e.getMessage());
            return false;
        }
    }
}