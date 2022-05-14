/**
 * This class represents a restaurant that has a name, address, a list of dishes and whether
 * its vegan.
 * Each restaurant is responsible for its dishes and to maintain the correct veganism
 * Each restaurant must present 6 to 17 plates (inclusively)
 * Each plate name must be unique
 **/

package restaurant;

import java.util.List;
import java.util.Objects;

public class Restaurant {
    private String name;
    private final String address;
    private List<Dish> dishes;
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
    public Restaurant(String name, String address, List<Dish> dishes) {
        this.name = name;
        this.address = address;
        this.dishes = dishes;
        this.isVegetarian = false;
    }

    public void setVegetarian(boolean vegetarian) {
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

    public boolean addDish(Dish d, int price) {
        d.setPrice(price);
        this.dishes.add(d);

        return true;
    }

    public boolean remove(String dishName) {
        return this.dishes.remove(getDishByName(dishName));
    }

    public int getPrice(String dishName) {
        Dish d = getDishByName(dishName);

        if (d != null) {
            return d.getPrice();
        } else {
            return -1;
        }
    }

    private Dish getDishByName(String dishName) {
        for (Dish d : this.dishes) {
            if (d.getName().equals(dishName)) {
                return d;
            }
        }

        return null;
    }

    public List<Dish> getDishes() {
        return this.dishes;
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
