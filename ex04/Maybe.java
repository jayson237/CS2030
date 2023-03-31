import java.util.function.Function;
import java.lang.Runnable;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

class Maybe<T> {
    private final T value;
    
    private Maybe(T value) {
        this.value = value;
    }

    static <U> Maybe<U> of(U value) {
        return new Maybe<U>(value);
    }

    static <T> Maybe<T> empty() {
        return new Maybe<T>(null);
    }

    private T get() {
        return this.value;
    }

    private boolean isEmpty() {
        return this.value == null;
    }

    private boolean isPresent() {
        return !this.isEmpty();
    }

    <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
        if (this.isEmpty()) {
            return Maybe.<R>empty();
        } else {
            return Maybe.<R>of(mapper.apply(this.value));
        }
    }

    <R> Maybe<R> flatMap(Function<? super T, ? extends Maybe<? extends R>> mapper) {
        if (this.value == null) {
            return Maybe.<R>empty();
        }
        Maybe<? extends R> maybeMapped = mapper.apply(this.value);
        Maybe<R> r = Maybe.<R>of(maybeMapped.orElse(null));
        return r;
    }

    Maybe<T> filter(Predicate<? super T> pred) {
        if (this.value == null || !pred.test(this.value)) {
            return this.empty();
        }
        return Maybe.<T>of(this.value);
    }

    void ifPresent(Consumer<? super T> action) {
        if (this.value !=  null) {
            action.accept(this.value);
        }
    }

    void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction) {
        if (this.value != null) {
            action.accept(this.value);
        } else {
            emptyAction.run();
        } 
    }

    Maybe<T> or(Supplier<? extends Maybe<? extends T>> supplier) {
        if (value != null) {
            return this;
        }
        Maybe<? extends T> result = supplier.get();
        return Maybe.<T>of(result.value);
    }

    T orElse(T other) {
        if (this.value == null) {
            return other;
        }
        return this.value;
    }

    T orElseGet(Supplier<? extends T> supplier) {
        return orElse(supplier.get());
    }
    
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

    @Override
    public String toString() {
        if (this.value == null) {
            return "Maybe.empty";
        } else {
            return "Maybe[" + this.value + "]";
        }
    }
}
