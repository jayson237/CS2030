abstract class TrafficLight {
    private final String color;

    TrafficLight(String color) {
        this.color = color;
    }

    abstract TrafficLight toggle();

    @Override
    public String toString() {
        return this.color;
    }
}
