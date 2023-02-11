class Serve extends Event {
    private final Server server;
    private final Customer customer;

    Serve(Server server, Customer customer, double time) {
        super(time);
        this.server = server;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return super.toString() + this.customer.toString() + " served by " + this.server.toString();
    }
}