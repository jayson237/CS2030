import java.util.function.Supplier;

class Wait extends Event {
    private final boolean keepWaiting;

    Wait(double time, Customer customer, Server server, boolean keepWaiting) {
        super(time, customer, server);
        this.keepWaiting = keepWaiting;
    }

    @Override
    Pair<Event, ImList<Server>> execute(int server, int queue, ImList<Server> servers,
                                        Supplier<Double> restTimes) {
        int serverId = super.getServerId();
        Server currServer = servers.get(serverId - 1);
        if (currServer.getIsUsedUntil() > super.getTime()) {
            return new Pair<>(new Wait(currServer.getIsUsedUntil(),
                    super.getCustomer(), currServer, true), servers);
        }
        return new Pair<>(new Serve(super.getIsUsedUntil(),
                super.getCustomer(), currServer, true), servers);
    }

    @Override
    double countWaitTime(ImList<Server> servers) {
        int serverId = super.getServerId();
        Server currServer = servers.get(serverId - 1);
        return currServer.getIsUsedUntil() - super.getTime();
    }

    @Override
    public String toString() {
        if (keepWaiting) {
            return "";
        }
        return String.format("%s %d waits at %d\n",
                super.toString(), super.getCustomerId(), super.getServerId());
    }
}
