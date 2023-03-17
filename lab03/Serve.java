class Serve extends Event {
    private final boolean waitServe;

    Serve(double time, Customer customer, Server server, boolean waitServe) {
        super(time, customer, server);
        this.waitServe = waitServe;
    }

    @Override
    Pair<Event, ImList<Server>> execute(int server, int queue, ImList<Server> servers) {
        int serverId = super.getServerId();
        Server currServer = servers.get(serverId - 1);
        double currTime = super.getTime();
        if (waitServe) {
            double serviceTime = servers.get(serverId - 1).getQCustomer().getService();
            servers = servers.set(serverId - 1,
                    new Server(currTime + serviceTime, serverId,
                            currServer.getQ().remove(0)));
            return new Pair<>(new Done(servers.get(serverId - 1).getIsUsedUntil(),
                    super.getCustomer(), currServer), servers);
        }
        servers = servers.set(serverId - 1, new Server(currTime +
                super.getCustomer().getService(), serverId));
        return new Pair<>(new Done(servers.get(serverId - 1).getIsUsedUntil(),
                super.getCustomer(), currServer), servers);
    }

    @Override
    int countServed() {
        return 1;
    }

    @Override
    public String toString() {
        return String.format("%s %d serves by %d\n", super.toString(),
                super.getCustomerId(), super.getServerId());
    }
}



