class TermAgent extends Term {

    TermAgent(String identifier) {
        super(identifier);
    }

    @Override
    public Host snd(Host host) {
        return new Snd(host, this);
    }
}
