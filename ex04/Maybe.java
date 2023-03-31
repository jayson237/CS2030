import java.util.function.Function;
import java.lang.Runnable;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Supplier;
// Optional of CS2030
class Maybe<T> {
    private final T object;

    private Maybe(T object) {
        this.object = object;
    }

    static <U> Maybe<U> of(U object) {
        if (object == null) {
            throw new NullPointerException();
        }
        return new Maybe<U>(object);
    }

    static <T> Maybe<T> empty() {
        return new Maybe<T>(null);
    }

    <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
        if (this.object == null) {
            return Maybe.<R>empty();
        } 
        return Maybe.<R>of(mapper.apply(this.object));
    }

    <R> Maybe<R> flatMap(Function<? super T, ? extends Maybe<? extends R>> mapper) {
        if (this.object == null) {
            return Maybe.<R>empty();
        }
        Maybe<? extends R> maybeMapped = mapper.apply(this.object);
        Maybe<R> r = Maybe.<R>of(maybeMapped.orElse(null));
        return r;   
    }

    static <U> Maybe<U> ofNullable(U object) {
        if (object == null) {
            return empty();
        }
        return of(object);
    }

    Maybe<T> filter(Predicate<? super T> pred) {
        if (this.object == null || !pred.test(this.object)) {
            return empty();
        }
        return Maybe.<T>of(this.object);
    }

    void ifPresent(Consumer<? super T> action) {
        if (this.object != null) {
            action.accept(this.object);
        }
    }

    void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction) {
        if (this.object != null) {
            action.accept(this.object);
        } else {
           emptyAction.run();
        } 
    }

    T orElse(T other) {
        if (this.object == null) {
            return other;
        }
        return this.object;
    }

    T orElseGet(Supplier<? extends T> supplier) {
        return orElse(supplier.get());
    }

    Maybe<T> or(Supplier<? extends Maybe<? extends T>> supplier) {
        if (object != null) {
            return this;
        }
        Maybe<? extends T> result = supplier.get();
        return Maybe.<T>of(result.object);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Maybe) {
            if (this.object == null && ((Maybe) o).object == null) {
                return true;
            }
            if (this.object == null || ((Maybe) o).object == null) {
                return false;
            }
            if (this.object.equals(((Maybe) o).object)) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public String toString() {
        if (this.object == null) {
            return "Maybe.empty";
        } else {
            return "Maybe[" + this.object + "]";
        }
    }
}
