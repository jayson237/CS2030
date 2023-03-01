class Snd extends Host {
    private final Term term;

    Snd(Host host, Term term) {
        super(host);
        this.term = term;
    }

    @Override
    public String toString() {
        return String.format("%s --->snd---> %s", term, super.toString());
    }
}
