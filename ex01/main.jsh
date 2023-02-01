double epsilon = 1E-15;

Circle createUnitCircle(Point p, Point q) { 
    Point midpoint = p.midPoint(q); 
    double length = p.distanceBetween(q) / 2; 
    double angle = p.angleTo(q) + (Math.PI / 2); 
    double distance = Math.sqrt(1 - (length * length));
    Point newCentre = midpoint.moveTo(angle, distance); 
    return new Circle(newCentre, 1.0); 
}

int findMaxDiscCoverage(ImList<Point> points) {
    int maxDiscCoverage = 0;
    int numOfPoints = points.size();
    for (int i = 0; i < numOfPoints - 1; i++) {
        for (int j = i + 1; j < numOfPoints; j++) {
            Point p = points.get(i);
            Point q = points.get(j);
            double distPQ = p.distanceBetween(q);
            if (distPQ < 2.0 + epsilon && distPQ > 0) {
                Circle c = createUnitCircle(p,q);
                int coverage = 0;
                for (Point point : points) {
                    if (c.contains(point)) {
                        coverage = coverage + 1;
                    }
                }
                if (coverage > maxDiscCoverage) {
                    maxDiscCoverage = coverage;
                }
            }
        }
    }
    return maxDiscCoverage;
}
