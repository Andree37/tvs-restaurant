package restaurant;

/**
 * This class is used to calculate discount.
 */
public class Discount {

    /**
     * This field represents discount, expressed in percent.
     */
    private final int discount;

    public Discount(int discount) {
        this.discount = discount;
    }

    public static Discount forZeroDiscount() {
        return new Discount(0);
    }

    int calculatePriceWithDiscount(int price) {
        double discountPercentage = this.discount / 100.0;
        int discountAbsolute = (int) (price * discountPercentage);
        return price - discountAbsolute;
    }
}
