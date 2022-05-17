/**
 * Entity that manages the information about the restaurants and client registered in the system.
 * Each restaurant is identified by its name.
 * Each Client is identified by their email address.
 **/


package restaurant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Rest {
    private final Set<Client> clients;
    private final Set<Restaurant> restaurants;
    private final String name;

    /**
     * Creates a new Rest entity with a given name and an empty set of clients and restaurants
     *
     * @param name the name of the entity
     */
    public Rest(String name) {
        this.name = name;
        this.clients = new HashSet<>();
        this.restaurants = new HashSet<>();
    }


    /**
     * Registers a new client.
     *
     * @param client client to be added to the set of clients
     * @throws ClientNotUniqueException if there is a client with the same email already registered
     */
    public void add(Client client) throws ClientNotUniqueException {
        boolean result = this.clients.add(client);
        // if result is false, a client with the same email already exists
        if (!result) {
            throw new ClientNotUniqueException();
        }
    }


    /**
     * Registers a new restaurant.
     *
     * @param restaurant restaurant to be added to the set of restaurants
     * @throws RestaurantNotUniqueException if there is a restaurant with the same name already registered
     */
    public void add(Restaurant restaurant) throws RestaurantNotUniqueException {
        boolean result = this.restaurants.add(restaurant);
        // if result is false, a restaurant with the same name already exists
        if (!result) {
            throw new RestaurantNotUniqueException();
        }
    }

    /**
     * @return a list of restaurants
     */
    public List<Restaurant> getRestaurants() {
        return new ArrayList<>(this.restaurants);
    }

    /**
     * @return a list of clients
     */
    public List<Client> getClients() {
        return new ArrayList<Client>(this.clients);
    }

    /**
     * Get a client by its email
     *
     * @param email the email of the wanted client
     * @return Client if the Client with the given email exists, null otherwise
     */
    public Client getClient(String email) {
        for (Client c : this.clients) {
            if (c.getEmail().equals(email)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Computes the discount for the specified client and shopping tray
     *
     * @param c    client to be discounted
     * @param tray of dishes from a restaurant
     * @return the discount that the client has
     */
    public int computeDiscount(Client c, ShoppingTray tray) {
        // idk
        return 0;
    }
}
