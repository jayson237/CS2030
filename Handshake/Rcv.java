class Rcv extends Term {
    private final Host snd;

    Rcv(Term term, Host snd) {
        super(term);
        this.snd = snd;
    }

    public Ack ack() {
        return new Ack(snd, this);
    }

    @Override
    public String toString() {
        return String.format("%s >--rcv--> %s", snd, super.toString());
    }
}
