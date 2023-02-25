class Arrive extends Event {
    private static final int type = 1;

    Arrive(double time, Customer customer, Server server) {
        super(time, customer, server);
    }
    
    @Override
    public int getNum() {
        return type;
    }

    @Override
    public Event execute(int server) {
        if (server != -1 && super.getServer().getNumPeopleInQ() == 0) {
            return new Serve(super.getTime(), super.getCustomer(), new Server(super.getDoneTime(),
                    server, super.getServer().getQMax(), super.getServer().getNumPeopleInQ()));
        } else if (server != -1 && super.getServer().getNumPeopleInQ() <= super.getServer().getQMax()) {
            return new Wait(super.getTime(), super.getCustomer(), new Server(super.getDoneTime(),
                    server, super.getServer().getQMax(), super.getServer().getNumPeopleInQ()));
        }
        return new Leave(super.getTime(), super.getCustomer(), super.getServer());
    }

    @Override
    public String toString() {
        return String.format("%s %d arrives\n", super.toString(), super.getCustomerId());
    }
}
