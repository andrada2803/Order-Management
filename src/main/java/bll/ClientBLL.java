package bll;

import bll_validators.EmailValidator;
import bll_validators.ClientAgeValidator;
import bll_validators.Validator;
import dao.ClientDAO;
import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL {
    private List<Validator<Client>> validators;
    ClientDAO clientDAO;

    /**
     * The constructor if the class, for the validation
     */
    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator());
        validators.add(new ClientAgeValidator());
        clientDAO = new ClientDAO(Client.class);
    }

    /**
     * This method looks for the client with the specified id and returns that client
     * @param id - the Client's identification number
     * @return - the client which has the wanted identification number
     *
     */
    public Client findClientById(int id) {
        Client cl = clientDAO.findById(id);
        if (cl == null) {
            throw new NoSuchElementException("The student with id =" + id + " was not found!");
        }
        return cl;
    }

    /**
     * @return - all the client from the database
     */
    public List<Client> findAll()
    {
        return clientDAO.findAll();
    }

    /**
     * Insert client
     * @param client - the client we want to insert into the database
     * @return - an integer which is -1 if it happens to be an exception
     */
    public int insertClient(Client client) {
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        return clientDAO.insert(client);
    }

    /**
     * Deletes the client with the specified id
     * @param id - the client's id which we want to delete
     */
    public void deleteClient(int id){
        clientDAO.delete(id);
    }

    /**
     * Edit client
     * @param client - the client we want to edit
     * @return - an integer which is -1 if it happens to be an exception
     */
    public int editClient(Client client){
        for (Validator<Client> validator : validators) {
            validator.validate(client);
        }
        return clientDAO.edit(client);
    }
}
