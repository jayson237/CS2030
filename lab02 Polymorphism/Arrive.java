class Arrive extends Event {
    Arrive(double time, Customer customer, Server server) {
        super(time, customer, server);
    }

    @Override
    public Event execute() {
        if (super.getServer().getServerId() != -1) {
            return new Serve(super.getTime(), super.getCustomer(),
                    new Server(super.getDoneTime(), super.getServer().getServerId()));
        }
        return new Leave(super.getTime(), super.getCustomer(), super.getServer());
    }

    @Override
    public String toString() {
        return String.format("%s %d arrives\n", super.toString(), super.getCustomerId());
    }
}
