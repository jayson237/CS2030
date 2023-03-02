class RcvTerm extends Term {
    private final Host snd;

    RcvTerm(Term term, Host snd) {
        super(term);
        this.snd = snd;
    }

    AckHost ack() {
        return new AckHost(snd, this);
    }

    @Override
    public String toString() {
        return String.format("%s >--rcv--> %s", snd, super.toString());
    }
}
