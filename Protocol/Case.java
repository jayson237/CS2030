import java.util.List;

class Case {
    private final Person person;
    private final ImList<Test> testHistory;
    private final Protocol currentProtocol;
    private final int numOfDays;
    private final ImList<Case> priorLineage;

    Case(Person person, PCR pcr) {
        this(person, new ImList<>(List.of(pcr)), getValidTestProtocol(person, pcr), 0, 
                new ImList<>());
    }

    Case(Person person, ImList<Test> testHistory, 
            Protocol currentProtocol, int numOfDays, ImList<Case> priorLineage) {
        this.person = person;
        this.testHistory = testHistory;
        this.currentProtocol = currentProtocol;
        this.numOfDays = numOfDays;
        this.priorLineage = priorLineage;
    }

    private static Protocol getValidTestProtocol(Person person, Test test) {    
        return test.isPositive() ? getValidPositiveTestProtocol(person, test)
                : new Discharged(DischargeStatus.DOCTOR_FOLLOW_UP);
    }

    static Protocol getValidPositiveTestProtocol(Person person, Test test) {
        return person.isHighRisk() ? new P1() : new P2();
    }

    Protocol getCurrentProtocol() {
        return this.currentProtocol;
    }

    Case test(Test test) {
        if (!test.isValid()) {
            return this;
        }
        return new Case(person, testHistory.add(test), currentProtocol.next(person, test, numOfDays
                + 1), numOfDays + 1, priorLineage);
    }

    ImList<Test> getTestHistory() {
        return this.testHistory;
    }

    ImList<Case> getLineage() {
        return this.priorLineage.add(this);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", person.toString(), testHistory.toString(),
                currentProtocol.toString());
    }
}
