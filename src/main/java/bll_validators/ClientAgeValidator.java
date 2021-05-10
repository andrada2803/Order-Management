package bll_validators;

import model.Client;

public class ClientAgeValidator implements Validator<Client> {
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 100;

    /**
     *
     * @param t - The Client which will be validated
     * Verifies if the client has the age between the minimum age and the maximum age in order to be able to place an order
     */
    public void validate(Client t) {

        if (t.getAge() < MIN_AGE || t.getAge() > MAX_AGE) {
            throw new IllegalArgumentException("The Client Age limit is not respected!");
        }

    }

}
