import java.util.function.Function;

abstract class Maybe<T> {

    abstract <R> Maybe<R> map(Function<? super T, ? extends R> mapper);
    
    static <T> Maybe<T> of(T value) {
        return new Maybe<T>() {
            private final T t = value;

            @Override
            public <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
                return Maybe.<R>of(mapper.apply(this.t));
            }

            @Override
            public String toString() {
                return "Maybe[" + this.t + "]";
            }
        };
    }

    static <T> Maybe<T> empty() {
        return new Maybe<T>() {
            @Override
            public <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
                return Maybe.<R>empty();
            }
            
            @Override
            public String toString() {
                return "Maybe.empty";
            }
        };
    }    
}
