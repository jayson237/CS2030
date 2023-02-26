class Leave extends Event {

    Leave(double time, Customer customer) {
        super(time, customer);
    }
    @Override
    public Pair<Event, ImList<Server>> execute(ImList<Server> serverList) {
        return new Pair<>(this, serverList);
    }
    
    @Override
    public String toString() {
        return String.format("%s %s leaves\n", super.toString(), super.getCustomer().toString());
    }
}

