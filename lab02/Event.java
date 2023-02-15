class Event {
    private final double time;
    private final Customer customer;

    private final Server server;

    public Event(double time, Customer customer, Server server) {
        this.time = time;
        this.customer = customer;
        this.server = server;
    }

    public double getTime() {
        return this.time;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public Server getServer() {
        return this.server;
    }

    public int getCustomerId() {
        return this.customer.getId();
    }

    public int eventState() {
        return 0;
    }

    public String returnType() {
        return "Event";
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.time);
    }
}
