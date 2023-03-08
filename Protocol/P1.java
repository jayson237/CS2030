class P1 implements Protocol {
    @Override
    public Protocol next(Person person, Test test, int numOfDays) { 
        if (!test.isValid()) {
            return this;
        }

        if (test.isPositive()) {
            return person.isHighRisk() ? this : new P2();
        }
        return new Discharged(DischargeStatus.DOCTOR_FOLLOW_UP);
    }

    @Override
    public String toString() {
        return "P1";
    }
}
