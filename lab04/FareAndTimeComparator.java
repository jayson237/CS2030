import java.util.Comparator;

class FareAndTimeComparator implements Comparator<Booking> {
    @Override
    public int compare(Booking a, Booking b) {
        return a.compareTo(b);    
    }
}
