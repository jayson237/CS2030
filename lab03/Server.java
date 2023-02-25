public class Server {
    private final double isUsedUntil;
    private final int serverId;
    private final int qmax;
    private final int numPeopleInQ;

    Server(double isUsedUntil, int serverId, int qmax, int numPeopleInQ) {
        this.isUsedUntil = isUsedUntil;
        this.serverId = serverId;
        this.qmax = qmax;
        this.numPeopleInQ = numPeopleInQ;
    }

    public double getIsUsedUntil() {
        return this.isUsedUntil;
    }

    public int getServerId() {
        return this.serverId;
    }

    public int getQMax() {
        return this.qmax;
    }

    public int getNumPeopleInQ() {
        return this.numPeopleInQ;
    }

    public boolean isAvailable(double time) {
        return this.isUsedUntil <= time;
    }

    public Server update(Customer customer) {
        return new Server(customer.getArrival() + customer.getService(),
                this.getServerId(), this.getQMax(), this.getNumPeopleInQ());
    }

    public Server incrementQueue() {
        return new Server(this.getIsUsedUntil(), this.getServerId(),
                this.getQMax(), this.getNumPeopleInQ() + 1);
    }

    public Server decrementQueue(Customer customer) {
        return new Server(this.getIsUsedUntil() + customer.getArrival() + customer.getService(), this.getServerId(),
                this.getQMax(), this.getNumPeopleInQ() - 1);
    }

    public String toString() {
        return String.format("%d", this.serverId);
    }
}
