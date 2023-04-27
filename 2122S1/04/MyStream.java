import java.util.function.Supplier;
import java.util.function.Consumer;
import java.util.function.Function;

abstract class MyStream<T> {
    
    abstract T get();

    abstract void forEach(Consumer<T> cons, int n);

    abstract <R> MyStream<R> map(Function<? super T,? extends R> func);
    
    static <T> MyStream<T> generate(Supplier<T> seed) {
        return new MyStream<T>() {
            public T get() {
                return seed.get();
            }

            public void forEach(Consumer<T> cons, int n) {
                for (int i = 0; i < n; i++) {
                    cons.accept(this.get());
                }
            }

            public <R> MyStream<R> map(Function<? super T,? extends R> func) {
                return MyStream.<R>generate(() -> func.apply(this.get()));
            }
        };
    }
}
