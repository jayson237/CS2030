class ART extends Test {
    private static final String invalid = "T";
    private static final String negative = "C";
    private static final String positive = "CT";

    ART(String result) {
        super(result);
    }

    @Override
    public boolean isValid() {
        return !getResult().equals(invalid);
    }

    @Override
    public boolean isPositive() {
        return getResult().equals(positive);
    }


    @Override
    public String toString() {
        return String.format("A%s", super.toString());
    }
}
    
