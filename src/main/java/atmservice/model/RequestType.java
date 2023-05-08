package atmservice.model;


public enum RequestType {
    STANDARD(4),
    SIGNAL_LOW(3),
    PRIORITY(2),
    FAILURE_RESTART(1);

    private final int priority;

    RequestType(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
