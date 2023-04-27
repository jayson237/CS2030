class RedLight extends TrafficLight {

    RedLight(String color) {
        super(color);
    }

    @Override
    public TrafficLight toggle() {
        return new AmberLight("amber", true);
    }
}
