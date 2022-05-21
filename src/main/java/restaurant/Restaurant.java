/**
 * This class represents a restaurant that has a name, address, a list of dishes and whether
 * its vegan.
 * Each restaurant is responsible for its dishes and to maintain the correct veganism
 * Each restaurant must present 6 to 17 plates (inclusively)
 * Each plate name must be unique
 **/

package restaurant;

import java.util.*;

public class Restaurant {

    private static final int MIN_DISHES = 6;
    private static final int MAX_DISHES = 17;

    private final String name;
    private final String address;
    private final Set<Dish> dishes;
    private boolean isVegetarian;

    /**
     * Creates a restaurant with a given name, address, and a list of plates, and
     * initializes veganism as
     * false
     *
     * @param name    name of the restaurant
     * @param address address of the restaurant
     * @param dishes  a list of dishes that have to be at least 6 and at most 17 different dishes (represented by their name)
     */
    public Restaurant(String name, String address, List<Dish> dishes) throws IllegalArgumentException {
        if (dishes == null || isInvalidAmountOfDishes(dishes.size())) {
            throw new IllegalArgumentException();
        }

        this.name = name;
        this.address = address;

        this.dishes = new HashSet<>(dishes);
        this.isVegetarian = false;
    }


    private boolean isInvalidAmountOfDishes(int size) {
        return size > MAX_DISHES || size < MIN_DISHES;
    }

    private Dish getDishByName(String dishName) {
        for (Dish d : this.dishes) {
            if (d.getName().equals(dishName)) {
                return d;
            }
        }

        return null;
    }

    private int getFreeDishes(Set<Dish> dishes) {
        int counter = 0;
        for (Dish d : dishes) {
            if (d.getPrice() == 0) {
                counter++;
            }
        }
        return counter;
    }

    private boolean doesExceedFreeDishes(int freeDishes) {
        // free dishes cannot have more than a quarter of the amount of dishes
        return freeDishes > dishes.size() / 4;
    }

    /**
     * @return true if restaurant is vegan, false otherwise
     */
    public boolean isVegetarian() {
        return this.isVegetarian;
    }

    /**
     * Sets the Restaurant as vegetarian
     *
     * @param vegetarian true for a vegetarian restaurant, false for otherwise
     * @throws InvalidInvocationException is thrown when we are setting a restaurant as vegetarian, but it has non-vegetarian plates
     */
    public void setVegetarian(boolean vegetarian) throws InvalidInvocationException {
        // check whether we can turn this restaurant vegan by going through the set of
        // dishes
        // if there is a dish that isn't vegetarian, the restaurant cannot be vegan
        if (vegetarian) {
            // check for non-vegan plates
            for (Dish d : this.dishes) {
                if (!d.isVegetarian()) {
                    throw new InvalidInvocationException("Restaurant contains non-vegetarian plates");
                }
            }
        }
        this.isVegetarian = vegetarian;
    }

    /**
     * @return the name of the restaurant
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the address of the restaurant
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Adds a new dish to the set of dishes. If the dish already exists, it updates the price
     *
     * @param d     the new dish to be inserted (it is made a new copy of it)
     * @param price the price, cannot be 0
     * @return true if it successfully inserted or updated a dish, false otherwise
     * @throws InvalidInvocationException If adding the dish goes over the amount that the restaurant can hold or if it goes over the amount of free dishes it can have
     */
    public boolean addDish(Dish d, int price)  {

        // check whether we can add a new dish and maintain within the maximum dishes
        if (isInvalidAmountOfDishes(this.dishes.size() + 1)) {
            return false;
        }
        if (price == 0 && doesExceedFreeDishes(getFreeDishes(this.dishes) + 1)) {
            // cannot have more free dishes
            return false;
        }

        // if we already have a dish with this name, we just update the price
        for (Dish td : this.dishes) {
            if (td.equals(d)) {
                td.setPrice(price);
                return false;
            }
        }

        // new dishes cannot be free
        if (price == 0) {
            return false;
        }

        // create a new dish to set the restaurant of that dish with the same information from d
        return this.dishes.add(new Dish(d.getName(), d.getDescription(), d.isVegetarian(), price, this));
    }

    /**
     * Removes a dish from the set of dishes of the restaurant which can be found by its name.
     *
     * @param dishName the name of the plate to be removed
     * @return true if the dish was removed, false otherwise
     * @throws InvalidInvocationException if removing the dish invalidates the state of the minimum amount of dishes or if removing a non-free dish, makes the amount of free dish go over the maximum.
     */
    public boolean remove(String dishName) throws InvalidInvocationException {
        // check whether we can remove a dish and maintain the minimum
        if (isInvalidAmountOfDishes(this.dishes.size() - 1)) {
            throw new InvalidInvocationException();
        }
        Dish d = getDishByName(dishName);
        // if dish is not free, and we are removing, we have to ensure that we don't go
        // over the quarter of free dishes
        if (d == null || d.getPrice() != 0 && doesExceedFreeDishes(getFreeDishes(this.dishes) - 1)) {
            throw new InvalidInvocationException();
        }

        return this.dishes.remove(d);
    }

    /**
     * Gets the price of a dish
     *
     * @param dishName name of the dish to be found by its name
     * @return the price of the dish if it exists, -1 otherwise
     */
    public int getPrice(String dishName) {
        Dish d = getDishByName(dishName);

        if (d != null) {
            return d.getPrice();
        } else {
            return -1;
        }
    }

    /**
     * @return a List of dishes
     */
    public List<Dish> getDishes() {
        return new ArrayList<>(this.dishes);
    }

    // Two customers are equal if their IDs are equal
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Restaurant r = (Restaurant) o;
        return Objects.equals(name, r.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
