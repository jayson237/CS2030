class Arrive extends Event {

    Arrive(double time, Customer customer, Server server) {
        super(time, customer, server);
    }

    @Override
    Pair<Event, ImList<Server>> execute(int server, int queue, ImList<Server> servers) {
        if (server != -1) {
            return new Pair<>(new Serve(super.getTime(), super.getCustomer(),
                    new Server(super.getIsUsedUntil(), server), false), servers);
        } else if (queue != -1) {
            Server currServer = servers.get(queue - 1);
            servers = servers.set(queue - 1, new Server(currServer.getIsUsedUntil(),
                    queue, currServer.getQ().add(super.getCustomer())));
            return new Pair<>(new Wait(super.getTime(), super.getCustomer(),
                    currServer, false), servers);
        }
        return new Pair<>(new Leave(super.getTime(), super.getCustomer(), super.getServer()),
                servers);
    }

    @Override
    public String toString() {
        return String.format("%s %d arrives\n", super.toString(), super.getCustomerId());
    }
}
