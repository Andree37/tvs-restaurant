/**
 * This class represents a client that has a name, an email and an address
 * It can also have a tax identification number
 **/

package restaurant;

import java.util.Objects;

public class Client {
    private final String name;
    private final String email;
    private int taxNumber;

    /**
     * Creates a new Client with a given name and email
     *
     * @param name  name of the client
     * @param email email of the client
     */
    public Client(String name, String email) {
        this(name, email, -1);
    }

    /**
     * Creates a new Client with a given name, email and tax Identification number
     *
     * @param name      name of the client
     * @param email     email of the client
     * @param taxNumber tax identification number of the client
     */
    public Client(String name, String email, int taxNumber) {
        this.name = name;
        this.email = email;
        this.taxNumber = taxNumber;
    }

    /**
     * @return the tax identification number of the client
     */
    public int getIdentificationNumber() {
        return this.taxNumber;
    }

    /**
     * Sets the client's tax identification number
     *
     * @param taxNumber the new tax identification number
     */
    public void setIdentificationNumber(int taxNumber) {
        this.taxNumber = taxNumber;
    }

    /**
     * @return the name of the client
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the email of the client
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @return the total value of shopping made by this client in the last 365 days
     */
    public float getAccumulatedShoppings() {
        // TODO THIS IDK WHAT TO DO
        return 1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Client client = (Client) o;
        return Objects.equals(email, client.email);
    }
}
