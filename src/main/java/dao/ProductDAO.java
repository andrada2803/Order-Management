package dao;

import model.Client;
import model.Product;

import java.util.List;

public class ProductDAO extends GenericDAO<Product>{

    /**
     * This is the constructor, in order to know which type of class will be used for the operations made in
     * the generic class
     * @param type - Product class type
     */
    public ProductDAO(Class<Product> type) {
        super(type);
    }

    /**
     * This method seraches for the client with the id equal with the one specified as a parameter
     * @param id - the identification number of a type of the Product
     * @return - the product which has the specified Id
     */
    public Product findById(int id){
        return super.findById(id);
    }

    /**
     * The methods display all the clients from the databse table
     * @return - the list with all the product from the database table
     */
    public List<Product> findAll(){
        return super.findAll();
    }

    /**
     * This method inserts a product into the database
     * @param product - the product which we want to insert into the table
     * @return - an integer which is -1 if there is an exception
     */
    public int insert(Product product){
        return super.insert(product);
    }

    /**
     * This method deletes the product which has the is specified
     * @param id - the identification number of the product
     */
    public void delete(int id){
        super.delete(id);
    }

    /**
     * This method edits a client from the database
     * @param product - the product which data we want to modify
     * @return - an integer which is -1 if there is an exception
     */
    public int edit(Product product){
        return super.edit(product);
    }
}
