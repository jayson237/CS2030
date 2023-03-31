import java.util.function.Supplier;

abstract class Event {
    private final double time;
    private final Customer customer;
    private final Server server;

    Event(double time, Customer customer, Server server) {
        this.time = time;
        this.customer = customer;
        this.server = server;
    }

    double getTime() {
        return this.time;
    }

    double getIsUsedUntil() {
        return this.server.getIsUsedUntil();
    }

    Customer getCustomer() {
        return this.customer;
    }

    Server getServer() {
        return this.server;
    }

    int getCustomerId() {
        return this.customer.getId();
    }

    int getServerId() {
        return this.server.getServerId();
    }

    int countServed() {
        return 0;
    }

    double countWaitTime(ImList<Server> servers, int numOfServers) {
        return 0;
    }

    abstract Pair<Event, ImList<Server>> execute(int numOfServes, int server, int queue,
                                                 ImList<Server> servers,
                                                 Supplier<Double> restTimes);

    @Override
    public String toString() {
        return String.format("%.3f", this.time);
    }
}
