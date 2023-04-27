class Point {
    private final double x;
    private final double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    private double getx() {
        return this.x;
    }

    private double gety() {
        return this.y;
    }

    double distanceTo(Point other) {
        double deltaY = other.gety() - this.gety();
        double deltaX = other.getx() - this.getx();
        return Math.sqrt(deltaY * deltaY + deltaX * deltaX);
    }

    @Override
    public String toString() {
        return String.format("point (%.1f, %.1f)", x, y);
    }
}

