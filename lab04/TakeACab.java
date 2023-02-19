class TakeACab extends Service {
    private static final int fare = 33;
    private static final int bookingFare = 200;

    TakeACab() {
        super();
    }

    public int computeFare(int d, int n, int t) {
        return d * fare + bookingFare;
    }

    @Override
    public String toString() {
        return "TakeACab";
    }
}
