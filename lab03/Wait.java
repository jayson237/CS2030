class Wait extends Event{

    Wait(double time, Customer customer, Server server) {
        super(time, customer, server);
    }

    @Override
    public Event execute(int server) {
        return new Serve(super.getWaitUntil(), super.getCustomer(), super.getServer());
    }

    @Override
    public String toString() {
        return String.format("%s %d waits at %d\n", super.toString(), super.getCustomerId(), 
                super.getServer().getServerId());
    }

}
