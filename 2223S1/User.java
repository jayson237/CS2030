class User implements Henry {
    private final String id;

    User(String id) {
        this.id = id;
    }

    Bot join(Bot bot) {
        return bot.connect(this);
    }  

    public void notify(String s) {
        System.out.println(this.id + ": " + s);
    }

    @Override
    public String toString() {
        return this.id;
    }
}
