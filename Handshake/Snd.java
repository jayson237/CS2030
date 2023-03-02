class Snd extends Host {
    private final Term term;

    Snd(Host host, Term term) {
        super(host);
        this.term = term;
    }

    public Rcv rcv() {
        return new Rcv(term, this);
    }

    @Override
    public String toString() {
        return String.format("%s >--snd--> %s", term, super.toString());
    }
}
