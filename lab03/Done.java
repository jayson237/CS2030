class Done extends Event {
    private final Server server;

    Done(double time, Customer customer, Server server) {
        super(time, customer);
        this.server = server;
    }

    @Override
    public Pair<Event, ImList<Server>> execute(ImList<Server> serverList) {
        return new Pair<>(this, serverList);
    }

    @Override
    public String toString() {
        return String.format("%s %s done serving by %s\n",
                super.toString(), super.getCustomer().toString(), this.server.toString());
    }

}

