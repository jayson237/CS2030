abstract class Event {
    private final double time;
    private final Customer customer;
    private final Server server;

    public Event(double time, Customer customer, Server server) {
        this.time = time;
        this.customer = customer;
        this.server = server;
    }

    public int getNum() {
        return 0;
    }

    public double getTime() {
        return this.time;
    }

    public double getDoneTime() {
        return this.customer.doneBy();
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public int getCustomerId() {
        return this.customer.getId();
    }

    public Server getServer() {
        return this.server;
    }

    public int getQueuePeople() {
        return this.server.getNumPeopleInQ();
    }

    public double getWaitUntil() {
        return this.getTime() + this.getServer().getIsUsedUntil();
    }

    abstract Event execute(int server);

    @Override
    public String toString() {
        return String.format("%.3f", this.time);
    }
}
