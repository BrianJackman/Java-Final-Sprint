package com.ecommerce.model;

/**
 * Represents a product in the store.
 */
public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private int sellerId;

    /**
     * Constructs a new Product.
     *
     * @param name the product name
     * @param price the product price
     * @param quantity the product quantity
     * @param sellerId the seller ID
     */
    public Product(String name, double price, int quantity, int sellerId) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.sellerId = sellerId;
    }

    /**
     * Constructs a new Product with all parameters.
     *
     * @param id the product ID
     * @param name the product name
     * @param price the product price
     * @param quantity the product quantity
     * @param sellerId the seller ID
     */
    public Product(int id, String name, double price, int quantity, int sellerId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.sellerId = sellerId;
    }

    // Product Setters
    /**
     * Sets the product ID.
     *
     * @param id the product ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the product name.
     *
     * @param name the product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the product price.
     *
     * @param price the product price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the product quantity.
     *
     * @param quantity the product quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Sets the seller ID.
     *
     * @param sellerId the seller ID
     */
    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    // Product Getters
    /**
     * Gets the product ID.
     *
     * @return the product ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the product name.
     *
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the product price.
     *
     * @return the product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the product quantity.
     *
     * @return the product quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets the seller ID.
     *
     * @return the seller ID
     */
    public int getSellerId() {
        return sellerId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", sellerId=" + sellerId +
                '}';
    }
}