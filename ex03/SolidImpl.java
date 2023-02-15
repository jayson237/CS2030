class SolidImpl implements Solid, Shape3D {
    private final Shape3D shape;
    private final double density;

    SolidImpl(Shape3D shape, double density) {
        this.shape = shape;
        this.density = density;
    }

    public double volume() {
        return this.shape.volume();
    }

    public double mass() {
        return this.volume() * this.density;
    }

    @Override
    public String toString() {
        return "SolidImpl";
    }
}
