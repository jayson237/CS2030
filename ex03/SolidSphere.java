class SolidSphere extends Sphere implements Solid {
    private final SolidImpl impl;

    SolidSphere(double radius, double density) {
        super(radius);
        this.impl = new SolidImpl(this, density);
    }

    public double mass() {
        return this.impl.mass();
    }


    @Override
    public String toString() {
        return String.format("solid-%s with a mass of %.2f", 
                super.toString(), this.mass());
    }
}

