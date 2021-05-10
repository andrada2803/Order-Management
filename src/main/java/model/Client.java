package model;

public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private int age;

    /**
     * The constructor with all args
     * @param id - the client's identification number
     * @param firstName - the client's first name
     * @param lastName - the client's last name
     * @param email - the client's email address
     * @param address - the client's address
     * @param age -the client's age
     */
    public Client(int id, String firstName, String lastName, String email, String address, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.age = age;
    }

    /**
     * The constructor without parameters and initializations
     */
    public Client() {
    }

    /**
     * Getters and Setters for all the client's attributes
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * The "toString" method overridden
     * @return - the String I want to write into the bill
     */
    @Override
    public String toString() {
        return "Client: id = " + id + ", first name = " + firstName + " last Name = " + lastName + ", address = " + address + ", email = " + email + ", age = " + age;
    }
}
