import java.util.function.Supplier;

class Serve extends Event {
    private final boolean waitServe;

    Serve(double time, Customer customer, Server server, boolean waitServe) {
        super(time, customer, server);
        this.waitServe = waitServe;
    }

    @Override
    Pair<Event, ImList<Server>> execute(int numOfServers, int server, int queue,
                                        ImList<Server> servers,
                                        Supplier<Double> restTimes) {
        int serverId = super.getServerId();
        Server currServer = servers.get(serverId - 1);
        double currTime = super.getTime();
        if (waitServe && serverId <= numOfServers) {
            double serviceTime = servers.get(serverId - 1).getQCustomer().getService();
            servers = servers.set(serverId - 1, new Server(currTime + serviceTime,
                    serverId, currServer.getQ().remove(0)));
            return new Pair<>(new Done(servers.get(serverId - 1).getIsUsedUntil(),
                    super.getCustomer(), currServer), servers);
        } else if (waitServe) {
            currServer = servers.get(numOfServers);
            double serviceTime = servers.get(numOfServers).getQCustomer().getService();
            if (serverId - 1 == numOfServers) {
                servers = servers.set(serverId - 1, new SelfCheck(currTime + serviceTime,
                        serverId, currServer.getQ().remove(0)));
            } else {
                servers = servers.set(numOfServers, new SelfCheck(currServer.getIsUsedUntil(),
                        numOfServers + 1, currServer.getQ().remove(0)));
                servers = servers.set(serverId - 1, new SelfCheck(currTime + serviceTime,
                        serverId));
            }
            return new Pair<>(new Done(servers.get(serverId - 1).getIsUsedUntil(),
                    super.getCustomer(), servers.get(serverId - 1)), servers);
        } else if (serverId > numOfServers) {
            double serviceTime = super.getCustomer().getService();
            servers = servers.set(serverId - 1, new SelfCheck(currTime + serviceTime, serverId));
            return new Pair<>(new Done(servers.get(serverId - 1).getIsUsedUntil(),
                    super.getCustomer(), servers.get(serverId - 1)), servers);
        }
        double serviceTime = super.getCustomer().getService();
        servers = servers.set(serverId - 1, new Server(currTime + serviceTime, serverId));
        return new Pair<>(new Done(servers.get(serverId - 1).getIsUsedUntil(),
                super.getCustomer(), currServer), servers);
    }

    @Override
    int countServed() {
        return 1;
    }

    @Override
    public String toString() {
        return String.format("%s %d serves by %s\n", super.toString(),
                super.getCustomerId(), super.getServer());
    }
}



