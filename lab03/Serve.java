class Serve extends Event {

    Serve(double time, Customer customer, Server server) {
        super(time, customer, server);
    }

    @Override
    Pair<Event, ImList<Server>> execute(int server, int queue, ImList<Server> servers) {
        // Update server available time for the second person in queue
        // and remove the person from queue if served
        if (super.getQueue().size() > 0) {
            double serviceTime = super.getQueue().get(0).getService();
            double currTime = super.getTime();
            Server currServer = servers.get(queue - 1);
            servers = servers.set(queue - 1,
                    new Server(currTime + serviceTime,queue,
                            currServer.getQ().remove(0)));
            return new Pair<>(new Done(servers.get(queue - 1).getIsUsedUntil(),
                    super.getCustomer(), super.getServer()), servers);
        }
        servers = servers.set(server - 1, new Server(super.getTime() +
                super.getCustomer().getService(), server));
        return new Pair<>(new Done(servers.get(server - 1).getIsUsedUntil(),
                super.getCustomer(), super.getServer()), servers);
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



