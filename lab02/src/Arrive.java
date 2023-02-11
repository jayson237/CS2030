class Arrive extends Event {
    Arrive(double time, Customer customer, Server server) {
        super(time, customer, server);
    }

    @Override
    public String returnType() {
        return "Arrive";
    }

    @Override
    public String toString() {
        return String.format("%s %d arrives\n", super.toString(), super.getCustomerId());
    }
}
