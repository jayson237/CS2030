class Rcv extends Term {
    private final Snd snd;

    Rcv(String identifier, Snd snd) {
        super(identifier);
        this.snd = snd;
    }

    @Override
    public String toString() {
        return String.format("%s --->rcv---> %s", snd, super.toString());
    }
}
