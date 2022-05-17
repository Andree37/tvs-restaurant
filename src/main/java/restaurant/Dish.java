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
    private final String description;
    private final boolean isVegetarian;
    private final Restaurant restaurant;
    private int price;

    /**
     * Creates a dish with a given name, description, whether it is vegetarian, it's
     * price and the restaurant
     *
     * @param name         the name of the dish
     * @param description  the description of the dish
     * @param isVegetarian whether the dish is vegetarian
     * @param price        the price of the dish
     * @param r            the restaurant that serves the dish
     */
    public Dish(String name, String description, boolean isVegetarian, int price, Restaurant r) {
        if (name == null || name.length() < 2 || name.length() > 5)
            throw new IllegalArgumentException("Invalid name");
        this.name = name;
        this.description = description;
        this.isVegetarian = isVegetarian;
        this.price = price;
        this.restaurant = r;
    }

    /**
     * @return the name of this dish
     **/
    public String getName() {
        return this.name;
    }

    /**
     * @return the Restaurant of this dish
     **/
    public Restaurant getRestaurant() {
        return this.restaurant;
    }

    /**
     * @return the price of this dish
     **/
    public int getPrice() {
        return this.price;
    }

    /**
     * Sets the price of this dish
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
     * @return the description of this dish
     **/
    public String getDescription() {
        return this.description;
    }

    /**
     * @return whether this dish is vegetarian or not
     **/
    public boolean isVegetarian() {
        return this.isVegetarian;
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
        return Objects.equals(name, dish.name);
    }
}
