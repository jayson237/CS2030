import java.util.function.Supplier;

class Wait extends Event {
    private final boolean keepWaiting;
    private final boolean findCounter;

    Wait(double time, Customer customer, Server server, boolean keepWaiting, boolean findCounter) {
        super(time, customer, server);
        this.keepWaiting = keepWaiting;
        this.findCounter = findCounter;
    }

    int findFastestCounter(int numOfServers, ImList<Server> servers) {
        double tempDelta = Double.POSITIVE_INFINITY;
        int serverId = 0;
        for (int i = numOfServers; i < servers.size(); i++) {
            double delta = servers.get(i).getIsUsedUntil() - super.getTime();
            if (delta < tempDelta) {
                serverId = i + 1;
                tempDelta = delta;
            }
        }
        return serverId;
    }


    @Override
    Pair<Event, ImList<Server>> execute(int numOfServers, int server, int queue,
                                        ImList<Server> servers,
                                        Supplier<Double> restTimes) {
        int serverId = super.getServerId();
        if (findCounter /*&& servers.get(numOfServers).getQCustomer() == super.getCustomer()*/) {
            serverId = findFastestCounter(numOfServers, servers);
        }
        Server currServer = servers.get(serverId - 1);
        if (serverId <= numOfServers && currServer.getIsUsedUntil() > super.getTime()) {
            return new Pair<>(new Wait(currServer.getIsUsedUntil(),
                    super.getCustomer(), currServer, true, false), servers);
        } else if (serverId > numOfServers && currServer.getIsUsedUntil() > super.getTime()) {
            return new Pair<>(new Wait(currServer.getIsUsedUntil(),
                    super.getCustomer(), currServer, true, true), servers);
        } else if (serverId > numOfServers && currServer.getIsUsedUntil() <= super.getTime()) {
            return new Pair<>(new Serve(currServer.getIsUsedUntil(),
                    super.getCustomer(), currServer, true), servers);
        }
        return new Pair<>(new Serve(super.getIsUsedUntil(),
                super.getCustomer(), currServer, true), servers);
    }

    @Override
    double countWaitTime(ImList<Server> servers, int numOfServers) {
        int serverId = super.getServerId();
        if (findCounter) {
            serverId = findFastestCounter(numOfServers, servers);
        }
        Server currServer = servers.get(serverId - 1);
        return currServer.getIsUsedUntil() - super.getTime();
    }

    @Override
    public String toString() {
        if (keepWaiting) {
            return "";
        }
        return String.format("%s %d waits at %s\n",
                super.toString(), super.getCustomerId(), super.getServer());
    }
}
