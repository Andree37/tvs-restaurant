/**
 * This class represents a ShoppingTray that each client has. It holds the dishes and the
 * corresponding amount of that the client wants to order.
 * It goes through many states, and they
 **/
package restaurant;

import java.util.HashMap;
import java.util.Map;

public class ShoppingTray {

    private final Map<Dish, Integer> shoppingTray;
    private StatusTray status;
    private int discount;

    /**
     * Creates a new empty shopping tray with the initial status as open and no discount
     */
    public ShoppingTray() {
        this.shoppingTray = new HashMap<>();
        this.status = StatusTray.OPEN;
        this.discount = 0;
    }

    /**
     * Updates the shopping tray with a new dish if it doesn't exist in it, or it updates the quantity.
     * If the resulting quantity is 0, the dish is removed
     *
     * @param dish     new dish to add
     * @param quantity the amount of that dish
     * @throws InvalidInvocationException if the status of the shopping tray isn't OPEN or if quantity is 0
     */
    public void update(Dish dish, int quantity) throws InvalidInvocationException {
        // can only change if the status is OPEN
        if (this.status != StatusTray.OPEN || quantity == 0) {
            throw new InvalidInvocationException("Wrong Status: " + this.status);
        }

        // if we already have this dish, just update quantity
        Integer previousQuantity = this.shoppingTray.get(dish);
        if (previousQuantity != null && previousQuantity + quantity > 0) {
            quantity += previousQuantity;
        }

        if (quantity == 0) {
            this.shoppingTray.remove(dish);

        } else {
            this.shoppingTray.put(dish, quantity);
        }
    }

    /**
     * Checks out the shopping tray in such a manner that:
     * If the shopping tray is open -> it closes it
     * If the shopping tray is closed -> it confirms it
     * else it is an invalid state
     *
     * @throws InvalidInvocationException if the shopping tray is empty or if there is an invalid state
     */
    public void checkout() throws InvalidInvocationException {
        if (shoppingTray.isEmpty()) {
            throw new InvalidInvocationException("Shopping tray is empty and it cannot be checked out");
        }
        switch (this.status) {
            case OPEN:
                this.status = StatusTray.CLOSED;
                break;
            case CLOSED:
                this.status = StatusTray.CONFIRMED;
                break;
            default:
                throw new InvalidInvocationException("Wrong Status: " + this.status);
        }
    }

    /**
     * Applies the discount to the shopping tray amount
     *
     * @param d the discount to be applied
     */
    public void applyDiscount(int d) { // it is Discount d on the project description why?
        this.discount = d; // should the discount be a class? talk to teacher
    }

    /**
     * Pays the shopping tray if the previous status is confirmed and passes it to paid.
     *
     * @throws InvalidInvocationException if the status is invalid
     */
    public void pay() throws InvalidInvocationException {
        if (this.status == StatusTray.CONFIRMED) {
            this.status = StatusTray.PAID;
        } else {
            throw new InvalidInvocationException("Wrong Status: " + this.status);
        }
    }

    /**
     * Cancels the shopping tray, returning it into the open status
     *
     * @throws InvalidInvocationException if the previous status is invalid
     */
    public void cancel() throws InvalidInvocationException {
        switch (this.status) {
            case CLOSED:
            case CONFIRMED:
                this.status = StatusTray.OPEN;
                break;
            default:
                throw new InvalidInvocationException("Wrong Status: " + this.status);
        }
    }

    /**
     * @return the total price of the dishes
     */
    public int computePrice() {
        int sum = 0;
        for (Map.Entry<Dish, Integer> entry : this.shoppingTray.entrySet()) {
            // the dish price * the amount of dishes
            sum += entry.getKey().getPrice() * entry.getValue();
        }
        return sum - this.discount;
    }

    /**
     * Aborts the shopping tray, where the status was confirmed, and it passes it to canceled
     *
     * @throws InvalidInvocationException if the previous state isn't confirmed
     */
    public void abort() throws InvalidInvocationException {
        if (this.status == StatusTray.CONFIRMED) {
            this.status = StatusTray.CANCELED;
        } else {
            throw new InvalidInvocationException("Wrong Status: " + this.status);
        }
    }

    /**
     * @return the current status of the shopping tray
     */
    public StatusTray getStatus() {
        return this.status;
    }

    /**
     * @return the map of dish and the amount
     */
    public Map<Dish, Integer> getContent() {
        return this.shoppingTray;
    }
}
