import java.util.function.BinaryOperator;
import java.util.Optional;
import java.lang.Comparable;

class Operator<T> implements Comparable<Operator<T>> {
    private final BinaryOperator<T> bo;
    private final int precedence;

    private Operator(BinaryOperator<T> bo, int precedence) {
        this.bo = bo;
        this.precedence = precedence;
    }

    static <T> Operator<T> of(BinaryOperator<T> bin, int n)  {
        return new Operator<T>(bin, n);    
    }

    T apply(T t1, T t2) {
        return bo.apply(t1, t2);
    }

    @Override
    public int compareTo(Operator<T> other) {
        return this.precedence - other.precedence;
    }

    @Override
    public String toString() {
        return String.format("Operator of precedence %d", precedence); 
    }

}
