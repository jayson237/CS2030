class Customer {
    private final int customerNo;
    private final double arrivalTime;
    private final double serviceTime;

    Customer(int customerNo, double arrivalTime, double serviceTime) {
        this.customerNo = customerNo;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public double getArrivalTime() {
        return this.arrivalTime;
    }

    public double getServiceTime() {
        return this.serviceTime;
    }

    @Override
    public String toString() {
        return " customer " + this.customerNo;
    }
}
