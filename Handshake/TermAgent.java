class TermAgent extends Term {

    TermAgent(String identifier) {
        super(identifier);
    }
    
    public Host snd(CompletedHost host) {
        return new Snd(host, this);
    }
}
