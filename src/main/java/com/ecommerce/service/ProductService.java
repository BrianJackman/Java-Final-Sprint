package com.ecommerce.service;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO;

    public ProductService(Connection connection) {
        this.productDAO = new ProductDAO(connection);
    }

    public void addProduct(Product product) throws SQLException {
        productDAO.addProduct(product);
    }

    public void updateProduct(Product product) throws SQLException {
        productDAO.updateProduct(product);
    }

    public void deleteProduct(int productId) throws SQLException {
        productDAO.deleteProduct(productId);
    }

    public Product getProductById(int productId) throws SQLException {
        return productDAO.getProductById(productId);
    }

    public List<Product> getAllProducts() throws SQLException {
        return productDAO.getAllProducts();
    }

    public List<Product> searchProductsByName(String productName) throws SQLException {
        return productDAO.searchProductsByName(productName);
    }

    public List<Product> getProductsBySeller(int sellerId) throws SQLException {
        return productDAO.getProductsBySeller(sellerId);
    }
}