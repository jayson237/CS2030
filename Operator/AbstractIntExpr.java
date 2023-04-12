abstract class AbstractIntExpr extends Expr<Integer> {
    protected static final Operator<Integer> addition = 
        Operator.<Integer>of((x, y) -> x + y, 4);
    protected static final Operator<Integer> multiplication = 
        Operator.<Integer>of((x, y) -> x * y, 3);

    protected AbstractIntExpr(Expr<Integer> expr) {
        super(expr);
    }
}
