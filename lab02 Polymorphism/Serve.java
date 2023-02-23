class Serve extends Event {
    Serve(double time, Customer customer, Server server) {
        super(time, customer, server);
    }

    @Override
    public Event execute(int server) {
        return new Done(super.getDoneTime(),super.getCustomer(), new Server(super.getDoneTime(), super.getServer().getServerId()));
    }

    @Override
    public String toString() {
        return String.format("%s %d serves by %d\n", super.toString(),
                super.getCustomerId(), super.getServer().getServerId());
    }
}



