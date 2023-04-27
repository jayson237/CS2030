class GreenLight extends TrafficLight {

    GreenLight(String color) {
        super(color);
    }

    @Override
    TrafficLight toggle() {
        return new AmberLight("amber", false);
    }
}
