class Pair<T, U> {
    private final T t;
    private final U u;

    Pair(T t, U u) {
        this.t = t;
        this.u = u;
    }

    static <T, U> Pair<T, U> of(T t, U u) {
        return new Pair<T, U>(t, u);
    }

    T first() {
        return this.t;
    }

    U second() {
        return this.u;
    }

    @Override
    public String toString() {
        return "(" + this.t + ", " + this.u + ")";
    }
}
