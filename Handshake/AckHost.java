class AckHost extends CompletedHost {
    private final Term rcv;

    AckHost(Host host, Term rcv) {
        super(host, rcv);
        this.rcv = rcv;
    }

    @Override
    public String toString() {
        return String.format("%s >--ack--> %s", rcv, super.toString());
    }
}
