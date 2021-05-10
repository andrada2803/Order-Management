package model;

public class Product {
    private int id;
    private String name;
    private int quantity;

    /**
     * The constructor with all args
     * @param id - the product's identification number
     * @param name - the name of the product
     * @param quantity - the quantity which is available
     */
    public Product(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    /**
     * The constructor without any parameter or initialization
     */
    public Product(){

    }

    /**
     * Getters and Setters for all the product's attributes
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * The "toString" method overridden
     * @return - the String I want to write into the bill
     */
    @Override
    public String toString() {
        return "Product: id = " + id + ", product = " + name + " quantity = " + quantity;
    }
}
