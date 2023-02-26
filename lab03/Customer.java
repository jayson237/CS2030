import java.util.function.Supplier;

class Customer {
    private final int customerId;
    private final double arrivalTime;
    private final Supplier<Double> serviceTime;

    Customer(int customerId, double arrivalTime, Supplier<Double> serviceTime) {
        this.customerId = customerId;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public int getCustomerId() {
        return this.customerId;
    }

    public double getArrivalTime() {
        return this.arrivalTime;
    }

    public double getServiceTime() {
        return this.serviceTime.get();
    }

    @Override
    public String toString() {
        return String.format("%d", this.customerId);
    }
}
