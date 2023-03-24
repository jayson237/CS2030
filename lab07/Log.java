import java.util.function.Function;

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

    static <T> Log<T> empty() {
        return new Log<T>(null);
    }

    static <T> Log<T> of(T t) {
        if (t instanceof Log || t == null) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        return new Log<T>(t);
    }

    static <T> Log<T> of(T t, String log) {
        if (t instanceof Log || t == null || log == null) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        return new Log<T>(t, log);
    }

    T orElse(T other) {
        if (this.t == null) {
            return other;
        }
        return this.t;
    }

    <R> Log<R> map(Function<? super T, ? extends R> mapper) {
       if (this.t == null)  {
           return Log.<R>empty();
       }
       return Log.<R>of(mapper.apply(this.t), this.log);
    }

    <R> Log<R> flatMap(Function<? super T, ? extends Log<? extends R>> mapper) {
        if (this.t == null) {
            return Log.<R>empty();
        }
        Log<? extends R> mapped = mapper.apply(this.t) ;
        Log<R> r = Log.<R>of(mapped.orElse(null));
        return r;
    }
    
    @Override
    public String toString() {
       if (this.t == null) {
           return "Log.empty";
       } else if (this.log != "" ) {
           return "Log[" + this.t + "]\n" + this.log;
       } else {
           return "Log[" + this.t + "]";
       }
    }
}

