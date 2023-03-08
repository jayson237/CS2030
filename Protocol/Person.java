class Person {
    private static final int vaccinated = 2;
    private static final int high = 8;
    private final String id;
    private final String status;
    private final int riskScore;

    Person(String id, String status, int riskScore) {
        this.id = id;
        this.status = status;
        this.riskScore = riskScore;
    }

    boolean isVaccinated() {
        return status.length() >= vaccinated;
    }

    boolean isHighRisk() {
        return riskScore >= high;
    }

    @Override
    public String toString() {
        return String.format("%s/%s/%s", id, status, isHighRisk() ? "HIGH" : "LOW");
    }
}
