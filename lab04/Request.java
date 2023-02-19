class Request {
    private final int distance;
    private final int numOfPass;
    private final int time;

    Request(int d, int n, int t) {
        this.distance = d;
        this.numOfPass = n;
        this.time = t;
    }
    
    public int computeFare(Service service) {
        return service.computeFare(this.distance, this.numOfPass, this.time);
    }

    public Service getCheapestService(Driver driver) {
        int cheapestFare = Integer.MAX_VALUE;
        Service cheapest = driver.getServices().get(0);
        for (Service s : driver.getServices()) {
            if (this.computeFare(s) < cheapestFare) {
                cheapestFare = this.computeFare(s);
                cheapest = s;
            }
        }
        return cheapest;
    }
    
    public int getCheapestFare(Driver driver) {
        int cheapestFare = Integer.MAX_VALUE;
        for (Service s : driver.getServices()) {
            if (this.computeFare(s) < cheapestFare) {
                cheapestFare = this.computeFare(s);
            }
        }
        return cheapestFare;
    }

    @Override
    public String toString() {
        return String.format("%dkm for %dpax @ %dhrs", this.distance, this.numOfPass, this.time);
    }
}
