class Done extends Event {
    Done(double time, Customer customer, Server server) {
        super(time, customer, server);
    }

    @Override
    public String returnType() {
        return "Done";
    }

    @Override
    public String toString() {
        return String.format("%.3f %d done serving by %d\n", super.getCustomer().doneBy(),
                super.getCustomerId(), super.getServer().getServerId());
    }

}
