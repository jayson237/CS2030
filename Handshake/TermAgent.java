class TermAgent extends Term {

    TermAgent(String identifier) {
        super(identifier);
    }
    
    public SndHost snd(CompletedHost host) {
        return new SndHost(host, this);
    }
}
