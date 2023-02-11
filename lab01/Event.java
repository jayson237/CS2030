class Event {
    private final double time;

    public Event(double time) {
        this.time = time;
    }

    public double getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.time);
    }
}