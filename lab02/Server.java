public class Server {
    private final double isUsedUntil;
    private final int serverId;

    Server(double isUsedUntil, int serverId) {
        this.isUsedUntil = isUsedUntil;
        this.serverId = serverId;
    }

    public int getServerId() {
        return this.serverId;
    }

    public boolean isAvailable(double time) {
        return this.isUsedUntil <= time;
    }

    public Server update(Customer other) {
        return new Server(other.getArrival() + other.getService(), 
                this.getServerId());
    }

    public String toString() {
        return String.format("%d", this.serverId);
    }
}
