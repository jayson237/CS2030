class Circle {
    private final Point centre;
    private final double radius;
    private static final double epsilon = 1E-15;

    Circle(Point centre, double radius) {
        this.centre = centre;
        this.radius = radius;
    }

    boolean contains(Point p) {
        return p.distanceBetween(this.centre) < this.radius + epsilon;
    }
    
    @Override
    public String toString() {
        return "circle of radius " + this.radius + 
            " centred at " + this.centre;
    } 
}
