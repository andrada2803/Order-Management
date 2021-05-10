package bll;

import dao.ProductDAO;
import model.Product;

import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {
    ProductDAO productDAO;

    /**
     * The constructor of the class
     */
    public ProductBLL() {
        productDAO = new ProductDAO(Product.class);
    }

    /**
     * This method looks for the product with the specified id and returns that product
     * @param id - the product's id which we want to find
     * @return - the product with the specified id
     */
    public Product findProductById(int id) {
        Product product = productDAO.findById(id);
        if (product == null) {
            throw new NoSuchElementException("The student with id =" + id + " was not found!");
        }
        return product;
    }

    /**
     * @return - all the products from the database
     */
    public List<Product> findAll()
    {
        return productDAO.findAll();
    }

    /**
     * Insert product
     * @param product - the product which we want to add to the database
     * @return - an integer which is -1 if it happens to be an exception
     */
    public int insertProduct(Product product) {

        return productDAO.insert(product);
    }

    /**
     * Delete product
     * @param id - the product's id which we want to delete
     */
    public void deleteProduct(int id){
        productDAO.delete(id);
    }

    /**
     * Edit product
     * @param product - the product we want to edit
     * @return - an integer which is -1 if it happens to be an exception
     */
    public int editProduct(Product product){

        return productDAO.edit(product);
    }
}
