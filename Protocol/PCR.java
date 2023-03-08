import java.util.List;

class PCR extends Test {
    private static final ImList<String> strains =
            new ImList<>(List.of("alpha", "beta", "delta", "omicron"));

    PCR(String result) {
        super(result);
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public boolean isPositive() {
        for (String strain : strains) {
            if (getResult().equals(strain)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("P%s", super.toString());
    }
}
