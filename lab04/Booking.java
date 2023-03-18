import java.lang.Comparable;

class Booking implements Comparable<Booking> {
    private final Driver driver;
    private final Request request;
    private final Service serviceType;
    private final double fare;
    private static final double centToDollar = 100;

    Booking(Driver driver, Request request) {
        this(driver, request, request.getCheapestService(driver), request.getCheapestFare(driver));
    }

    Booking(Driver driver, Request request, Service serviceType, int fare) {
        this.driver = driver;
        this.request = request;
        this.serviceType = serviceType;
        this.fare = fare / centToDollar;
    }

    public ImList<Booking> getAllPossibleBookings() {
        ImList<Booking> allBookings = new ImList<Booking>();
        for (Service s : this.driver.getServices()) {
            allBookings = 
            allBookings.add(new Booking(this.driver, this.request, s, this.request.computeFare(s)));
        }
        return allBookings;
    }

    @Override
    public int compareTo(Booking other) {
        if (this.fare == other.fare) {
            return this.driver.shorterWaitTime(other.driver);
        } else if (this.fare > other.fare) {
            return 1;
        }
        return -1;
    }

    @Override
    public String toString() {
        return String.format("$%.2f using %s (%s)", this.fare, 
                this.driver.toString(), this.serviceType.toString());
    }

    public String toStringThis() {
        return String.format("$%.2f using %s (%s)", this.fare, 
                this.driver.toString(), this.serviceType.toString());
    }
}
