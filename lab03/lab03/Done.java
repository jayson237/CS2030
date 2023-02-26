class Done extends Event {
    private final Server server;

    Done(double time, Customer customer, Server server) {
        super(time, customer);
        this.server = server;
    }

    public double getArrivalTime() {
        return super.getArrivalTime();
    }

    public Customer getCustomer() {
        return super.getCustomer();
    }

    @Override
    public Pair<Event, ImList<Server>> execute(ImList<Server> serverList) {
        return new Pair<>(this, serverList);
    }

    @Override
    public String toString() {
        return String.format("%s %s done serving by %s\n",
                super.toString(), this.getCustomer().toString(), this.server.toString());
    }

}

