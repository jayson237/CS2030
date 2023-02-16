class Leave extends Event {
    Leave(double time, Customer customer, Server server) {
        super(time, customer, server);
    }

    @Override
    public Event execute() {
        return this;
    }

    @Override
    public String toString() {
        return String.format("%s %d leaves\n", super.toString(), super.getCustomerId());
    }
}
