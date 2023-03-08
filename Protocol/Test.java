abstract class Test {
    private final String result;

    Test(String result) {
        this.result = result;
    }

    String getResult() {
        return result;
    }

    @Override
    public String toString() {
        if (!isValid()) {
            return "X";
        }
        return isPositive() ? "+" : "-";
    }

    abstract boolean isValid();
    
    abstract boolean isPositive();
}
