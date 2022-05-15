package restaurant;

import java.util.HashMap;
import java.util.Map;

public class ShoppingTray {

    private Map<Dish, Integer> shoppingTray;
    private StatusTray status;
    private int discount;

    public ShoppingTray() {
        this.shoppingTray = new HashMap<>();
        this.status = StatusTray.OPEN;
        this.discount = 0;
    }

    public void update(Dish dish, int quantity) {
        // can only do changes if the status is OPEN
        if (this.status != StatusTray.OPEN || quantity <= 0) {
            return;
        }

        // if we already have this dish, just update quantity
        Integer previousQuantity = this.shoppingTray.get(dish);
        if (previousQuantity != null && previousQuantity + quantity > 0) {
            quantity += previousQuantity;
        }

        this.shoppingTray.put(dish, quantity);
    }

    public void checkout() {
        this.status = StatusTray.CONFIRMED;
    }

    public void applyDiscount(int d) { // it is Discount d on the project description why?
        this.discount = d; // should the discount be a class? talk to teacher
    }

    public void pay() {
        if (this.status == StatusTray.CONFIRMED) {
            this.status = StatusTray.PAID;
        }
    }

    public void cancel() {
        this.status = StatusTray.OPEN;
    }

    public int computePrice() {
        int sum = 0;
        for (Dish d : this.shoppingTray.keySet()) {
            sum += d.getPrice();
        }
        return sum - this.discount;
    }

    public void abort() {
        this.status = StatusTray.CANCELED;
    }

    public StatusTray getStatus() {
        return this.status;
    }

    public Map<Dish, Integer> getContent() {
        return this.shoppingTray;
    }
}
