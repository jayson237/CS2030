class Customer {
    private final int id;
    private final double arrival;
    private final double service;

    Customer(int id, double arrival, double service) {
        this.id = id;
        this.arrival = arrival;
        this.service = service;
    }

    public int getId() {
        return this.id;
    }

    public double getArrival() {
        return this.arrival;
    }

    public double getService() {
        return this.service;
    }

    public double doneBy() {
        return getArrival() + getService();
    }

    @Override
    public String toString() {
        return String.format("%d", this.id);
    }
}
