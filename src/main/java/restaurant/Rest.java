package restaurant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Rest {
    private String name;
    private final Set<Client> clients;
    private final Set<Restaurant> restaurants;

    public Rest(String name) {
        this.name = name;
        this.clients = new HashSet<>();
        this.restaurants = new HashSet<>();
    }

    // registers a new client. The email of client must be unique. If not, throws
    // ClientNotUniqueException
    public void add(Client client) {
        boolean result = this.clients.add(client);
    }

    // registers a new restaurant. The name of this restaurant must be unique.
    // throws RestaurantNotUniqueException if name is not unique
    public void add(Restaurant restaurant) {
        boolean result = this.restaurants.add(restaurant);
    }

    public List<Restaurant> getRestaurants() {
        return new ArrayList<Restaurant>(this.restaurants);
    } // gets all restaurants

    public List<Client> getClients() {
        return new ArrayList<Client>(this.clients);
    } // gets all clients

    public Client getClient(String email) {
        for (Client c : this.clients) {
            if (c.getEmail().equals(email)) {
                return c;
            }
        }
        return null;
    } // gets the client with the specified email

    // computes the discount for the specified client and shopping tray
    public int computeDiscount(Client c, ShoppingTray tray) {
        // idk
        return 0;
    }
}
