import java.util.stream.IntStream;

class IntExpr extends AbstractIntExpr {
    private static final Operator<Integer> sub = Operator.<Integer>of((x, y) -> x - y, 4);
    private static final Operator<Integer> div = Operator.<Integer>of((x, y) -> x / y, 3);
    private static final Operator<Integer> exp = 
        Operator.<Integer>of((x, y) -> IntStream.range(0, y).reduce(1, (a, b) -> a * x), 2);

    IntExpr(Expr<Integer> expr) {
        super(expr);
    }

    static IntExpr of(Integer n) {
        return new IntExpr(Expr.<Integer>of(n));
    }

    IntExpr add(int n) {
        return new IntExpr(super.op(addition, n));
    }

    IntExpr mul(int n) {
        return new IntExpr(super.op(multiplication, n));
    }

    IntExpr sub(int n) {
        return new IntExpr(super.op(sub, n));
    }

    IntExpr div(int n) {
        return new IntExpr(super.op(div, n));
    }

    IntExpr exp(int n) {
        return new IntExpr(super.op(exp, n));
    }

}
