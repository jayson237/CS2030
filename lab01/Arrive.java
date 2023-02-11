class Arrive extends Event {
    private final Customer customer;

    Arrive(Customer customer, double time) {
        super(time);
        this.customer = customer;
    }

    @Override
    public String toString() {
        return super.toString() + this.customer.toString() + " arrives";
    }
}