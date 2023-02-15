class Leave extends Event {
    Leave(double time, Customer customer, Server server) {
        super(time, customer, server);
    }

    @Override
    public String returnType() {
        return "Leave";
    }

    @Override
    public String toString() {
        return String.format("%s %d leaves\n", super.toString(), super.getCustomerId());
    }
}
