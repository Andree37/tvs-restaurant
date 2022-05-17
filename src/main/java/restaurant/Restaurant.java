/**
 * This class represents a restaurant that has a name, address, a list of dishes and whether
 * its vegan.
 * Each restaurant is responsible for its dishes and to maintain the correct veganism
 * Each restaurant must present 6 to 17 plates (inclusively)
 * Each plate name must be unique
 **/

package restaurant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
     * @param name
     * @param address
     * @param dishes
     */
    public Restaurant(String name, String address, List<Dish> dishes) throws IllegalArgumentException {
        if (!enoughDishes(dishes)) {
            throw new IllegalArgumentException();
        }

        this.name = name;
        this.address = address;

        this.dishes = new HashSet<>(dishes);
        this.isVegetarian = false;
    }

    private boolean enoughDishes(List<Dish> dishes) {
        return dishes != null && enoughDishesSize(dishes.size());
    }

    private boolean enoughDishesSize(int size) {
        return size <= MAX_DISHES && size >= MIN_DISHES;
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

    public void setVegetarian(boolean vegetarian) throws InvalidInvocationException {
        // check whether we can turn this restaurant vegan by going through the set of
        // dishes
        // if there is a dish that isn't vegetarian, the restaurant cannot be vegan

        if (vegetarian) {
            // check for non vegan plates
            for (Dish d : this.dishes) {
                if (!d.isVegetarian()) {
                    throw new InvalidInvocationException();
                }
            }
        }
        this.isVegetarian = vegetarian;
    }

    public boolean isVegetarian() {
        return this.isVegetarian;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public boolean addDish(Dish d, int price) throws IllegalArgumentException, InvalidInvocationException {

        // check whether we can add a new dish and maintain within the maximum dishes
        if (!enoughDishesSize(this.dishes.size() + 1)) {
            throw new InvalidInvocationException();
        }
        if (price == 0 && doesExceedFreeDishes(getFreeDishes(this.dishes) + 1)) {
            // cannot have more free dishes
            throw new InvalidInvocationException();
        }

        // if we already have a dish with this name, we just update the price
        for (Dish td : this.dishes) {
            if (td.equals(d)) {
                td.setPrice(price);
                return true;
            }
        }

        // new dishes cannot be free
        if (price == 0) {
            return false;
        }

        d.setPrice(price);
        return this.dishes.add(d);
    }

    public boolean remove(String dishName) throws InvalidInvocationException {
        // check whether we can remove a dish and maintain the minimum
        if (!enoughDishesSize(this.dishes.size() - 1)) {
            throw new InvalidInvocationException();
        }
        Dish d = getDishByName(dishName);
        // if dish is not free and we are removing, we have to ensure that we don't go
        // over the quarter of free dishes
        if (d == null || d.getPrice() != 0 && doesExceedFreeDishes(getFreeDishes(this.dishes) - 1)) {
            throw new InvalidInvocationException();
        }

        return this.dishes.remove(d);
    }

    public int getPrice(String dishName) {
        Dish d = getDishByName(dishName);

        if (d != null) {
            return d.getPrice();
        } else {
            return -1;
        }
    }

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
        return name == r.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
