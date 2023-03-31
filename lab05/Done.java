import java.util.function.Supplier;

class Done extends Event {
    Done(double time, Customer customer, Server server) {
        super(time, customer, server);
    }

    @Override
    Pair<Event, ImList<Server>> execute(int numOfServers, int server, int queue,
                                        ImList<Server> servers,
                                        Supplier<Double> restTimes) {
        int serverId = super.getServerId();
        Server currServer = servers.get(serverId - 1);
        servers = servers.set(serverId - 1, currServer.rest(restTimes));
        return new Pair<>(this, servers);
    }

    @Override
    public String toString() {
        return String.format("%s %d done serving by %s\n", super.toString(),
                super.getCustomerId(), super.getServer());
    }
}
