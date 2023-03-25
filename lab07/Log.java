import java.util.function.Function;
import java.util.Optional;

class Log<T> {
    private final T t;
    private final String log;

    private Log(T t) {
        this.t = t;
        this.log = "";
    }

    private Log(T t, String log) {
        this.t = t;
        this.log = log;
    }

    static <T> Log<T> of(T t) {
        return Log.<T>of(t, ""); 
    }

    static <T> Log<T> of(T t, String log) {
        return Optional.ofNullable(t)
            .filter(obj -> !(obj instanceof Log))
            .flatMap(obj -> Optional.ofNullable(log)
            .map(str -> new Log<T>(t, log)))
            .orElseThrow(() -> new IllegalArgumentException("Invalid arguments"));
    }

    T get() {
        return this.t;
    }
    
    <R> Log<R> map(Function<? super T, ? extends R> mapper) {
        return Log.<R>of(mapper.apply(this.t), this.log);
    }

    <R> Log<R> flatMap(Function<? super T, ? extends Log<? extends R>> mapper) {
        Log<? extends R> mapped = mapper.apply(this.t);
        if (this.log == "" && mapped.log != "") {
            return Log.<R>of(mapped.get(), mapped.log);
        } else if (mapped.log == "") {
            return Log.<R>of(mapped.get(), this.log);
        }
        return Log.<R>of(mapped.get(), this.log + "\n" + mapped.log);
    }

    @Override
    public boolean equals(Object o) { 
        if (o instanceof Log) {
            if (this.t.equals(((Log) o).t) && this.log.equals(((Log) o).log)) {
                return true;
            }
        }    
        return false;
    }

    @Override
    public String toString() {
        if (this.log != "") {
            return "Log[" + this.t + "]\n" + this.log;
        } 
        return "Log[" + this.t + "]";
    }
}

