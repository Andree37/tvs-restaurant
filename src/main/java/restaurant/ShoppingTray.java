package restaurant;

import java.util.HashMap;
import java.util.Map;

public class ShoppingTray {

    private final Map<Dish, Integer> shoppingTray;
    private StatusTray status;
    private int discount;

    public ShoppingTray() {
        this.shoppingTray = new HashMap<>();
        this.status = StatusTray.OPEN;
        this.discount = 0;
    }

    public void update(Dish dish, int quantity) {
        // can only do changes if the status is OPEN
        if (this.status != StatusTray.OPEN || quantity == 0) {
            return;
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

    public void checkout() {
        if (shoppingTray.isEmpty()) {
            // throw an error
            return;
        }
        switch (this.status) {
            case OPEN:
                this.status = StatusTray.CLOSED;
                break;
            case CLOSED:
                this.status = StatusTray.CONFIRMED;
                break;
            default:
                // throw error
                break;
        }
    }

    public void applyDiscount(int d) { // it is Discount d on the project description why?
        this.discount = d; // should the discount be a class? talk to teacher
    }

    public void pay() {
        if (this.status == StatusTray.CONFIRMED) {
            this.status = StatusTray.PAID;
        } else {
            // throw exception
        }
    }

    public void cancel() {
        switch (this.status) {
            case CLOSED:
            case CONFIRMED:
                this.status = StatusTray.OPEN;
                break;
            default:
                // throw error
                break;
        }
        // if here throw error of invalid state
    }

    public int computePrice() {
        int sum = 0;
        for (Dish d : this.shoppingTray.keySet()) {
            sum += d.getPrice();
        }
        return sum - this.discount;
    }

    public void abort() {
        if (this.status == StatusTray.CONFIRMED) {
            this.status = StatusTray.CANCELED;
        } else {
            // throw expection
        }
    }

    public StatusTray getStatus() {
        return this.status;
    }

    public Map<Dish, Integer> getContent() {
        return this.shoppingTray;
    }
}
