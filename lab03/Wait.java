class Wait extends Event {
    Wait(double time, Customer customer, Server server) {
        super(time, customer, server);
    }

    @Override
    Pair<Event, ImList<Server>> execute(int server, int queue, ImList<Server> servers) {
        return new Pair<>(new Serve(super.getIsUsedUntil(),
                super.getCustomer(), super.getServer()), servers);
    }

    @Override
    public String toString() {
        return String.format("%s %d waits at %d\n",
                super.toString(), super.getCustomerId(), super.getServerId());
    }
}
