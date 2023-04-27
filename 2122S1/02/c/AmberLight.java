class AmberLight extends TrafficLight {

    AmberLight(String color) {
        super(color);
    }

    @Override
    TrafficLight toggle() {
        return new RedLight("red");
    }
}

