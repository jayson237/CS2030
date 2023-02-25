class Serve extends Event {
    private static final int type = 2;
    Serve(double time, Customer customer, Server server) {
        super(time, customer, server);
    }

    @Override
    public int getNum() {
        return type;
    }

    @Override
    public Event execute(int server) {
        return new Done(super.getDoneTime(), super.getCustomer(), super.getServer());
    }

    @Override
    public String toString() {
        return String.format("%s %d serves by %d\n", super.toString(),
                super.getCustomerId(), super.getServer().getServerId());
    }
}



