import java.util.function.Supplier;

public class Server {
    private final double isUsedUntil;
    private final int serverId;
    private final ImList<Customer> queue;

    Server(double isUsedUntil, int serverId, ImList<Customer> queue) {
        this.isUsedUntil = isUsedUntil;
        this.serverId = serverId;
        this.queue = queue;
    }

    Server(double isUsedUntil, int serverId) {
        this.isUsedUntil = isUsedUntil;
        this.serverId = serverId;
        this.queue = new ImList<Customer>();
    }

    double getIsUsedUntil() {
        return this.isUsedUntil;
    }

    int getServerId() {
        return this.serverId;
    }

    ImList<Customer> getQ() {
        return this.queue;
    }

    Customer getQCustomer() {
        return this.queue.get(0);
    }

    int getQSize() {
        return this.queue.size();
    }

    boolean isAvailable(double time) {
        return this.isUsedUntil <= time;
    }

    Server rest(Supplier<Double> restTimes) {
        return new Server(this.getIsUsedUntil() + restTimes.get(), serverId, queue);
    }

    public String toString() {
        return String.format("%d", this.serverId);
    }
}
