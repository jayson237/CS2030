import java.util.Optional;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.BinaryOperator;

class DnC<T,R> {
    private final Supplier<T> p;
    private final Predicate<T> pred;
    private final Function<T,R> f;
    private final Function<T,Pair<Supplier<T>,Supplier<T>>> pair;

    protected DnC(T p, Predicate<T> pred, Function<T,R> f) {
        this.p = () -> p;
        this.pred = pred;
        this.f = f;
        this.pair = t -> Pair.of(() -> t, () -> t);
    }

    protected DnC(Supplier<T> p, Predicate<T> pred, Function<T,R> f,
            Function<T,Pair<Supplier<T>,Supplier<T>>> pair) {
        this.p = p;
        this.pred = pred;
        this.f = f;
        this.pair = pair;
    }

    static <T,R> DnC<T,R> of(T p, Predicate<T> pred, Function<T,R> f) {
        return new DnC<T,R>(p, pred, f);
    }

    static <T,R> DnC<T,R> of(T p, Predicate<T> pred, Function<T,R> f,
            Function<T,Pair<T,T>> pair) {
        Function<T,Pair<Supplier<T>,Supplier<T>>> newPair = pair
            .andThen(x -> Pair.of(() -> x.first(), () -> x.second()));
        return new DnC<T,R>(() -> p, pred, f, newPair);
    }

    static <T,R> DnC<T,R> of(Supplier<T> p, Predicate<T> pred, Function<T,R> f,
            Function<T,Pair<Supplier<T>,Supplier<T>>> pair) {
        return new DnC<T,R>(p, pred, f, pair);
    }

    void peek(Consumer<T> action) {
        action.accept(this.p.get());
    }

    Optional<R> solve() {
        return Optional.of(this.p.get()).filter(pred).map(f);
    }

    Optional<R> solve(BinaryOperator<R> bin) {
        T p = this.p.get();
        DnC<T, R> left = DnC.of(() -> Optional.of(p)
                .filter(pred)
                .orElseGet(() -> {
                    Pair<Supplier<T>, Supplier<T>> newPair = pair.apply(p);
                    return newPair.first().get();
                }), pred, f, pair);

        DnC<T, R> right = DnC.of(() -> Optional.of(p)
                .filter(pred)
                .orElseGet(() -> {
                    Pair<Supplier<T>, Supplier<T>> newPair = pair.apply(p);
                    return newPair.second().get();
                }), pred, f, pair);

        R result = Optional.of(p).filter(pred).map(f).orElseGet(() -> bin
                .apply(left.solve(bin).orElseThrow(),
                        right.solve(bin).orElseThrow()));
        return Optional.of(result);
    }

    DnC<T,R> left() {
        T left = Optional.of(this.p.get())
            .filter(pred)
            .orElseGet(() -> {
                Pair<Supplier<T>, Supplier<T>> newPair = pair.apply(this.p.get());
                return newPair.first().get();
            });
        return DnC.of(() -> left, pred, f, pair);
    }

    DnC<T,R> right() {
        T right = Optional.of(this.p.get())
                .filter(pred)
                .orElseGet(() -> {
                    Pair<Supplier<T>, Supplier<T>> newPair = pair.apply(this.p.get());
                    return newPair.second().get();
                });
        return DnC.of(() -> right, pred, f, pair);
    }
}
