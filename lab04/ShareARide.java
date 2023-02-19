class ShareARide extends Service {
    private static final int rate = 50;
    private static final int surcharge = 500;

    ShareARide() {
        super();
    }

    public int computeFare(int d, int n, int t) {
        if (super.isPeakHourRide(t)) {
            return (d * rate + surcharge) / n;
        }
        return (d * rate) / n;
    }
    
    @Override
    public String toString() {
        return "ShareARide";
    }
}
