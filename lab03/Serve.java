class Serve extends Event {
    private final Server server;

    Serve(double time, Customer customer, Server server) {
        super(time, customer);
        this.server = server;
    }

    @Override
    public Pair<Event, ImList<Server>> execute(ImList<Server> serverList) {
        return new Pair<>(new Done(super.getArrivalTime() +
            super.getCustomer().getServiceTime(), this.getCustomer(), this.server), serverList);
    }

    @Override
    public int getStatistics() {
        return 1;
    }

    @Override
    public String toString() {
        return String.format("%s %s serves by %s\n",
                super.toString(), super.getCustomer().toString(), this.server.toString());
    }
}