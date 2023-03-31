import java.util.function.Supplier;

class Leave extends Event {
    Leave(double time, Customer customer, Server server) {
        super(time, customer, server);
    }

    @Override
    Pair<Event, ImList<Server>> execute(int numOfServers, int server, int queue,
                                        ImList<Server> servers,
                                        Supplier<Double> restTimes) {
        return new Pair<>(this, servers);
    }

    @Override
    public String toString() {
        return String.format("%s %d leaves\n", super.toString(), super.getCustomerId());
    }
}
