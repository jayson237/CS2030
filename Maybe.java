import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Function;

interface Maybe<T> {
    void ifPresent(Consumer<? super T> action);
    Maybe<T> filter(Predicate<? super T> pred);
    <R> Maybe<R> map(Function<? super T, ? extends R> mapper);
    
    static <T> Maybe<T> empty() {
        return Maybe.<T>of(null); // mistake was Maybe.of<T>(null).. >.<
    }
    
    static <T> Maybe<T> of(T value) {
        return new Maybe<T>() {
            private final T t = value;

            public void ifPresent(Consumer<? super T> action) { // action.accept(..)
                if (this.isPresent()) {
                    action.accept(this.get());
                }
            }

            public Maybe<T> filter(Predicate<? super T> pred) {
                return this.isEmpty() ? this :
                    (pred.test(this.get()) ? this : Maybe.<T>empty());
            }

            public <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
                return this.isEmpty() ? Maybe.<R>empty() :
                    Maybe.<R>of(mapper.apply(this.get()));
            }
/*
            @Override
            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                } else if (obj instanceof Maybe<?> other) {
                    if (this.isEmpty()) {
                        return other.isEmpty();
                    } else {
                        return !other.isEmpty() && this.get().equals(other.get());
                    }
                } else {
                    return false;
                }
            }
*/
            private T get() {
                return this.t;
            }

            private boolean isEmpty() {
                return this.get() == null;
            }

            private boolean isPresent() {
                return !this.isEmpty();
            }

            public String toString() {
                return (this.t == null) ? "Maybe.empty" : "Maybe[" + this.t + "]";
            }
        };
    }
}
