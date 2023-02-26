class Server {

    private final int serverId;
    private final double endTime;
    private final ImList<Wait> queue;
    private final int qmax;

    Server(int serverId, double endTime, ImList<Wait> queue, int qmax) {
        this.serverId = serverId;
        this.endTime = endTime;
        this.queue = queue;
        this.qmax = qmax;
    }

    public int getQMax() {
        return this.qmax;
    }

    public int getServerId() {
        return this.serverId;
    }

    public double getEndTime() {
        return this.endTime;
    }

    public ImList<Wait> getQueue() {
        return this.queue;
    }

    public boolean isQueueAvailable() {
        return this.queue.size() < qmax;
    }

    public boolean isAvailable(Customer customer) {
        return customer.getArrivalTime() >= this.endTime;
    }

    public Server updateServer(Customer customer) {
        return new Server(this.serverId, customer.getArrivalTime() +
            customer.getServiceTime(), queue, qmax);
    }

    public Server updateQueue(ImList<Wait> updatedQueue) {
        return new Server(this.serverId, this.endTime, updatedQueue, this.qmax);
    }

    @Override
    public String toString() {
        return String.format("%d", this.serverId);
    }
}