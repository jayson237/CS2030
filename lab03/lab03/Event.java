abstract class Event {
    private final double time;
    private final Customer customer;

    Event(double time, Customer customer) {
        this.time = time;
        this.customer = customer;
    }

    public int getCustomerId() {
        return this.customer.getCustomerId();
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public double getArrivalTime() {
        return this.time;
    }

    abstract Pair<Event, ImList<Server>> execute(ImList<Server> serverList);

    public double getAverageWaiting() {
        return 0;
    }

    public int getStatistics() {
        return 0;
    }

    public int getWaitEvent() {
        return 0;
    }
    
    @Override
    public String toString() {
        return String.format("%.3f", this.time);
    }
}
