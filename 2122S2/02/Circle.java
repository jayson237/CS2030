class Circle {
    private final Point centre;
    private final double r;
    private static final double epsilon = 1E-15;

    Circle(Point centre, double r) {
        this.centre = centre;
        this.r = r;
    }

    boolean contains(Point p) {
        return p.distanceTo(this.centre) < this.r + epsilon;
    }

    @Override
    public String toString() {
        return String.format("Circle centered at %s with radius %.1f", centre, r);
    }
}
