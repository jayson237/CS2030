class P2 implements Protocol {
    private static final int isolation = 3;
    private static final int vaccinatedDischarge = 7;
    private static final int unvaccinatedDischarge = 14;
    
    @Override
    public Protocol next(Person person, Test test, int numOfDays) {
        if (numOfDays >= isolation && !test.isPositive()    
                || (person.isVaccinated() && numOfDays >= vaccinatedDischarge)
                || (!person.isVaccinated() && numOfDays >= unvaccinatedDischarge)) {
            return new Discharged(DischargeStatus.COMPLETE);
        }
        return this;
    }

    @Override
    public String toString() {
        return "P2";
    }
}
