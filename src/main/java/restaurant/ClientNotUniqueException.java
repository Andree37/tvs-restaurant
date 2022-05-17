package restaurant;

public class ClientNotUniqueException extends RuntimeException {

    public ClientNotUniqueException(String message) {
        super(message);
    }

    public ClientNotUniqueException() {
        super();
    }
}