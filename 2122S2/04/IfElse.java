import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

class IfElse<T, R> {
    private final Predicate<T> pred;
    private final Function<T,R> t;
    private final Function<T,R> f;

    IfElse(Predicate<T> pred, Function<T,R> t, Function<T,R> f) {
        this.pred = pred;
        this.t = t;
        this.f = f;
    }

    static <T,R> IfElse<T,R> of(Predicate<T> pred, Function<T,R> t, Function<T,R> f) { 
        return new IfElse<T,R>(pred, t, f);
    }

    <U> IfElse<T,U> map(Function<R, U> mapper) {
        return IfElse.<T,U>of(pred, t.andThen(mapper), f.andThen(mapper));
    }

    <U> IfElse<T, U> flatMap(Function<? super R, ? extends IfElse<R, ? extends U>> mapper) {
        return IfElse.<T, U>of(pred,
                x -> mapper.apply(t.apply(x)).apply(t.apply(x)),
                x -> mapper.apply(f.apply(x)).apply(f.apply(x)));
    }
    
    R apply(T condition) {
        return Optional.of(condition).filter(pred).map(t)
            .orElse(Optional.of(condition).map(f)
                    .orElseThrow());
    }
   
    /*
    R apply(T condition) {
        return pred.test(condition) ? t.apply(condition) : f.apply(condition);
    }
    */

    IfElse<T,R> mapIf(IfElse<T,R> other) {
        Predicate<T> smooyyyyy = this.pred.and(other.pred);
        return IfElse.of(smooyyyyy, t, f); 
    }

    IfElse<T,R> mapElse(IfElse<T,R> other) {
        Predicate<T> smooyyyyy = this.pred.or(other.pred);
        return IfElse.of(smooyyyyy, t, f);
    }
}
