package model;

public class Orders {
    private int id;
    private int clientId;
    private int productId;
    private int quantity;

    /**
     * The constructor with all args
     * @param id - the order's identification number
     * @param clientId - the client's identification number
     * @param productId - the product's identification number
     * @param quantity - the quantity that the client wants to buy
     */
    public Orders(int id, int clientId, int productId, int quantity) {
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
    }

    /**
     * The constructor without any parameter or initialization
     */
    public Orders(){
    }

    /**
     * Getters and setters for all the order's attributes
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * The "toString" method overridden
     * @return the String I want to write into the bill
     */
    @Override
    public String toString() {
        return "Order: id = " + id + ", client = " + clientId + ", product = " + productId + " quantity = " + quantity;
    }
}
