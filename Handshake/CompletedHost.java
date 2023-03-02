abstract class CompletedHost extends Host {
    CompletedHost(String identifier) {
        super(identifier);
    }

    CompletedHost(Host host, Term term) {
        super(host, term);
    }
}
