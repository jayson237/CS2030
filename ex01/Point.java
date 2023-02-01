class Point {
    private final ImList<Double> coord;

    Point(double x, double y) {
        this.coord = new ImList<Double>().add(x).add(y);
    }

    private double getx() {
        return this.coord.get(0);
    }

    private double gety() {
        return this.coord.get(1);
    }

    public Point midPoint(Point m) {
        double x = (this.getx() + m.getx()) / 2;
        double y = (this.gety() + m.gety()) / 2;
        return new Point(x, y);
    }

    public double distanceBetween(Point p) {
        double deltaY = p.gety() - this.gety();
        double deltaX = p.getx() - this.getx();
        return Math.sqrt(deltaY * deltaY + deltaX * deltaX);
    }

    public double angleTo(Point m) {
        double deltaY = m.gety() - this.gety();
        double deltaX = m.getx() - this.getx();
        if (deltaX == 0 && deltaY == 0 || (m.gety() == this.gety()) && (m.getx() > this.getx())) {
            return 0;
        } else if ((m.gety() == this.gety()) && (m.getx() < this.getx())) {
            return Math.PI;
        } else if (deltaY < 0 && deltaX < 0) {
            return Math.atan(deltaY / deltaX) - Math.PI;
        }
        return Math.atan(deltaY / deltaX);
    }

    public Point moveTo(double angle, double d) {
        return new Point(this.getx() + d * Math.cos(angle), 
        this.gety() + d * Math.sin(angle));
    }

    @Override
    public String toString() {
        return ("point (" + 
                String.format("%.3f", this.getx()) + 
                ", " + String.format("%.3f", this.gety()) +
                ")");
    }
}
