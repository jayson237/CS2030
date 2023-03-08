import java.util.List;

class ContactCase extends Case {
    ContactCase(Person person, Test test, Case sourceOfContact) {
        super(person, new ImList<>(List.of(test)), getValidTestProtocol(person, test),
                0, sourceOfContact.getLineage());
    }

    private static Protocol getValidTestProtocol(Person person, Test test) {    
        return test.isPositive() ? Case.getValidPositiveTestProtocol(person, test) : new P3();
    }
}
