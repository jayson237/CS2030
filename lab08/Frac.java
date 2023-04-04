class Frac extends Pair<Num, Num> {
    private Frac(Num n, Num d) {
        super(n, d);
    }

    static Frac of(Num n, Num d) {
        return new Frac(n, d);
    }

    @Override
    public String toString() {
        return String.format("%s : %s", first(), second());
    }
}
