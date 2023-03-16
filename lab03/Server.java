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
        this.queue = new ImList<>();
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

    int getQSize() {
        return this.queue.size();
    }

    boolean isAvailable(double time) {
        return this.isUsedUntil <= time;
    }

    public String toString() {
        return String.format("%d", this.serverId);
    }
}
