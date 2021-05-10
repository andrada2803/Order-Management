package dao;
import java.util.List;

import model.Client;

public class ClientDAO extends GenericDAO<Client>{

    /**
     * This is the constructor, in order to know which type of class will be used for the operations made in
     * the generic class
     * @param type - Client class type
     */
    public ClientDAO(Class<Client> type) {
        super(type);
    }


    /**
     * This method searches for the client with the id equal with the one specified as a parameter
     * @param id - the identification number of the Client
     * @return - the client with the specified id
     */
    public Client findById(int id){
        return super.findById(id);
    }

    /**
     * The methods display all the clients from the databse table
     * @return - all the client from the database table
     */
    public List<Client> findAll(){
        return super.findAll();
    }

    /**
     * This method inserts a client into the database
     * @param client - the client which we want to insert into the table
     * @return - an integer which is -1 if there is an exception
     */
    public int insert(Client client){
        return super.insert(client);
    }

    /**
     * This method deletes the client which has the is specified
     * @param id - the identification number of the client
     */
    public void delete(int id){
        super.delete(id);
    }

    /**
     * This method edits a client from the database
     * @param client - the client which data we want to modify
     * @return - an integer which is -1 if there is an exception
     */
    public int edit(Client client){
        return super.edit(client);
    }
}
