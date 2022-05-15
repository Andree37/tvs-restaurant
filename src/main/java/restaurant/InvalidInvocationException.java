package restaurant;

public class InvalidInvocationException extends RuntimeException {

    public InvalidInvocationException(String message) {
        super(message);
    }

    public InvalidInvocationException() {
        super();
    }
}
