import java.util.function.Function;
import java.lang.Runnable;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

interface Maybe<U> {

    U get();
    
    boolean isEmpty();

    U orElse(U other);

    <R> Maybe<R> map(Function<? super U, ? extends R> mapper);
    
    <R> Maybe<R> flatMap(Function<? super U, ? extends Maybe<? extends R>> mapper);

    Maybe<U> filter(Predicate<? super U> pred);
    
    void ifPresent(Consumer<? super U> action);

    void ifPresentOrElse(Consumer<? super U> action, Runnable emptyAction);

    U orElseGet(Supplier<? extends U> supplier);

    Maybe<U> or(Supplier<? extends Maybe<? extends U>> supplier);

    static <U> Maybe<U> empty() {
        return Maybe.<U>of(null);
    }

    static <U> Maybe<U> of(U value) {
        return new Maybe<U>() {
            private final U u = value;
            
            public U get() {
                return this.u;
            }

            public boolean isEmpty() {
                return this.u == null;
            }

            private boolean isPresent() {
                return !this.isEmpty();
            }

            public <R> Maybe<R> map(Function<? super U, ? extends R> mapper) {
                if (this.isEmpty()) {
                    return Maybe.<R>empty();
                } else {
                    return Maybe.<R>of(mapper.apply(this.u));
                }
            }

            public <R> Maybe<R> flatMap(Function<? super U, ? extends Maybe<? extends R>> mapper) {
                if (this.isEmpty()) {
                    return Maybe.<R>empty();
                }
                Maybe<? extends R> maybeMapped = mapper.apply(this.u);
                Maybe<R> r = Maybe.<R>of(maybeMapped.orElse(null));
                return r;
            }

            public Maybe<U> filter(Predicate<? super U> pred) {
                if (this.isEmpty() || !pred.test(this.u)) {
                    return empty(); 
                }
                return Maybe.<U>of(this.u);
            }

            public void ifPresent(Consumer<? super U> action) {
                if (!this.isEmpty()) {
                    action.accept(this.u);
                }
            }

            public void ifPresentOrElse(Consumer<? super U> action, Runnable emptyAction) {
                if (!this.isEmpty()) {
                    action.accept(this.u);
                } else {
                    emptyAction.run();
                } 
            }

            public U orElse(U other) {
                if (this.isEmpty()) {
                    return other;
                }
                return this.u;
            }

            public U orElseGet(Supplier<? extends U> supplier) {
                return orElse(supplier.get());
            }

            public Maybe<U> or(Supplier<? extends Maybe<? extends U>> supplier) {
                if (!this.isEmpty()) {
                    return this;
                }
                Maybe<? extends U> result = supplier.get();
                return result.map(x -> x);
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
                if (this.u == null) {
                    return "Maybe.empty";
                } else {
                    return "Maybe[" + this.u + "]";
                }
            }

        };
    }
}





