class AmberLight extends TrafficLight {

    private boolean isNextGreen;

    AmberLight(String color, boolean isNextGreen) {
        super(color);
        this.isNextGreen = isNextGreen;
    }

    @Override
    TrafficLight toggle() {
        if (isNextGreen) {
            return new GreenLight("green");
        }
        return new RedLight("red");
    }
}

