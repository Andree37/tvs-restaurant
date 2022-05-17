package restaurant;

public class RestaurantNotUniqueException extends RuntimeException {

    public RestaurantNotUniqueException(String message) {
        super(message);
    }

    public RestaurantNotUniqueException() {
        super();
    }
}