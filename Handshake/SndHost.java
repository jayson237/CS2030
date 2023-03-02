class SndHost extends Host {
    private final Term term;

    SndHost(Host host, Term term) {
        super(host);
        this.term = term;
    }

    RcvTerm rcv() {
        return new RcvTerm(term, this);
    }

    @Override
    public String toString() {
        return String.format("%s >--snd--> %s", term, super.toString());
    }
}
