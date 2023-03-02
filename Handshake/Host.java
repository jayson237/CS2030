abstract class Host {
    private final String identifier;
    private final ImList<Term> terms;

    Host(String identifier, ImList<Term> terms) {
        this.identifier = identifier;
        this.terms = terms;
    }
   
    Host(String identifier) {
        this(identifier, new ImList<>());
    }

    Host(Host host) {
        this(host.identifier, host.terms);
    }

    Host(Host host, Term term) {
        this(host.identifier, host.terms.add(term));
    }

    public void broadcast() {
        for (Term t: terms) {
            t.ping();
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Host h) {
            return h.identifier == this.identifier;
        }
        return false;
    } 


    @Override
    public String toString() {
        return this.identifier;
    }
}
