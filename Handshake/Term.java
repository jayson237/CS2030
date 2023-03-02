abstract class Term {
    private final String identifier;

    Term(String identifier) {
        this.identifier = identifier;
    }

    Term(Term term) {
        this(term.identifier);
    }

    public void ping() {
        System.out.println(String.format("%s:beep", identifier));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Term t) {
            return t.identifier == this.identifier;
        }
        return false;
    } 

    @Override
    public String toString() {
        return this.identifier;
    }
}
