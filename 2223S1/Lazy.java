import java.util.function.Supplier;
import java.util.function.Function;
import java.util.Optional;

class Lazy<T> {
    private final Supplier<? extends T> supplier;

    private Optional<T> cache;
    
    private Lazy(Supplier<? extends T> supplier) {
        this.supplier = supplier;
        this.cache = Optional.<T>empty();
    }
    
    static <T> Lazy<T> of(Supplier<? extends T> supplier) {
        return new Lazy<T>(supplier);
    }

    public T get() {
        T v = this.cache.orElseGet(this.supplier);
        this.cache = Optional.<T>of(v);
        return v;
    }

    <R> Lazy<R> map(Function<? super T, ? extends R> mapper) {
        return Lazy.<R>of(() -> mapper.apply(this.get()));
    }

    <R> Lazy<R> flatMap(Function<? super T, ? extends Lazy<? extends R>> mapper) {
        return Lazy.<R>of(() -> mapper.apply(this.get()).get());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Lazy<?> other) {
            return this.get().equals(other.get());
        } 

        return false;
    }
}
