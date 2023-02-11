class Serve extends Event {
    Serve(double time, Customer customer, Server server) {
        super(time, customer, server);
    }

    @Override
    public String returnType() {
        return "Serve";
    }

    @Override
    public String toString() {
        return String.format("%s %d serves by %d\n", super.toString(),
                super.getCustomerId(), super.getServer().getServerId());
    }
}



