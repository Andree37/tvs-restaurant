package restaurant;

public enum StatusTray {
    OPEN, CLOSED, CONFIRMED, PAID, CANCELED;

    @Override
    public String toString() {
        switch (this) {
            case OPEN:
                return "Open";
            case CLOSED:
                return "Closed";
            case CONFIRMED:
                return "Confirmed";
            case PAID:
                return "Paid";
            case CANCELED:
                return "Cancelled";
            default:
                return "Unknown";
        }
    }
}