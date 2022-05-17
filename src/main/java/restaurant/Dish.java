/**
 * This class represents a dish that has a name, price, description and whether
 * its vegan.
 * Each dish is associated with a restaurant.
 **/

package restaurant;

import java.util.Objects;

public class Dish {
    private static final int MAX_PRICE = 15;

    private final String name;
    private String description;
    private boolean isVegetarian;
    private int price;
    private Restaurant restaurant;

    /**
     * Creates a dish with a given name, description, whether it is vegetarian, it's
     * price and the restaurant
     * 
     * @param name
     * @param description
     * @param isVegetarian
     * @param price
     * @param r
     */
    public Dish(String name, String description, boolean isVegetarian, int price, Restaurant r) {
        this.name = name;
        this.description = description;
        this.isVegetarian = isVegetarian;
        this.price = price;
        this.restaurant = r;
    }

    /**
     * @returns the name of this dish
     **/
    public String getName() {
        return this.name;
    }

    /**
     * @returns the restaurant related to this dish
     **/
    public Restaurant getRestaurant() {
        return this.restaurant;
    }

    /**
     * @returns the price of this dish
     **/
    public int getPrice() {
        return this.price;
    }

    /**
     * Sets the new price of the dish
     * 
     * @param newPrice the new price of the dish
     */
    void setPrice(int newPrice) throws IllegalArgumentException {
        if (newPrice > MAX_PRICE || newPrice < 0) {
            throw new IllegalArgumentException();
        }
        this.price = newPrice;
    }

    /**
     * @returns the description of this dish
     **/
    public String getDescription() {
        return this.description;
    }

    /**
     * @returns whether this dish is vegetarian or not
     **/
    public boolean isVegetarian() {
        return this.isVegetarian;
    }

    public Dish(String name) {
        if (name == null || name.length() < 2 || name.length() > 5)
            throw new IllegalArgumentException("no name");
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Dish dish = (Dish) o;
        return name == dish.name;
    }
}
