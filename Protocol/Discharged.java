class Discharged implements Protocol {
    private final DischargeStatus dischargeStatus;

    Discharged(DischargeStatus dischargeStatus) {
        this.dischargeStatus = dischargeStatus;
    }

    @Override
    public Protocol next(Person person, Test test, int numOfDays) {
        return test.isPositive() ? new Discharged(DischargeStatus.SEEK_MEDICAL_ATTENTION) : this;
    }

    private String getStatusText() {
        switch (dischargeStatus) {
            case COMPLETE:
                return "complete";
            case DOCTOR_FOLLOW_UP:
                return "follow up with doctor";
            default:
                return "seek medical attention";
        }
    }

    @Override
    public String toString() {
        return String.format("Discharged: %s", getStatusText());
    }
}
