
package restaurant;

import java.util.Objects;

public class Client {
    private final String name;
    private final String email;
    private int taxNumber;

    public Client(String name, String email) {
        this.name = name;
        this.email = email;
        this.taxNumber = -1;
    }

    public void setIdentificationNumber(int taxNumber) {
        this.taxNumber = taxNumber;
    }

    public int getIdentificationNumber() {
        return this.taxNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    // returns the total value of shopping made by this client in the last 365 days
    public float getAccumulatedShoppings() {
        return 1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Client client = (Client) o;
        return Objects.equals(name, client.name) && Objects.equals(email, client.email);
    }
}
