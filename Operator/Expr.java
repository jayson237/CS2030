import java.util.Optional;
import java.util.function.Supplier;

class Expr<T> {
    private final Supplier<T> left;
    private final Optional<Operator<T>> operator;
    private final Supplier<Optional<Expr<T>>> right;

    Expr(Supplier<T> left, Optional<Operator<T>> operator, 
            Supplier<Optional<Expr<T>>> right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
    
    Expr(Expr<T> expr) {
        this.left = expr.left;
        this.operator = expr.operator;
        this.right = expr.right;
    }

    static <T> Expr<T> of(T t) {
        return new Expr<T>(() -> t, Optional.empty(), () -> Optional.empty());
    }

    Expr<T> op(Operator<T> oper, Expr<T> exp) {
        return op(oper, () -> Optional.of(Expr.of(exp.evaluate()))); 
    }

    Expr<T> op(Operator<T> oper, T other) {
        return op(oper, () -> Optional.of(Expr.of(other)));
    }

    Expr<T> op(Operator<T> oper, Supplier<Optional<Expr<T>>> other) {
        return this.operator.map(x -> x.compareTo(oper) <= 0 ?
                new Expr<T>(() -> this.evaluate(), Optional.of(oper), other) : 
                new Expr<T>(left, operator, () -> right.get().map(y -> y.op(oper, other))))
            .orElse(new Expr<T>(left, Optional.of(oper), other));
    }

    T evaluate() {
        return this.operator.map(x -> x.apply(this.left.get(), this.right.get()
                    .map(y -> y.evaluate()).orElseThrow()))
            .orElseGet(this.left);
    }

    @Override
    public String toString() {
        return "(" + this.evaluate() + ")";
    }
}
