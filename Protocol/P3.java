class P3 implements Protocol {
    private static final int MONITORING_PERIOD = 5;

    @Override
    public Protocol next(Person person, Test test, int numOfDays) {
        if (test.isPositive()) {
            return person.isHighRisk() ? new P1() : new P2();
        }

        if (numOfDays >= MONITORING_PERIOD) {   
            return new Discharged(DischargeStatus.COMPLETE);
        }
        return this;
    }

    @Override
    public String toString() {
        return "P3";
    }
}
