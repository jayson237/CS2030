import java.util.Comparator;

class EventComparator implements Comparator<Event> {
    public int compare(Event e1, Event e2) {
        double timeDelta = e1.getTime() - e2.getTime();
        int priorCustomer = e1.getCustomerId() - e2.getCustomerId();
        if (timeDelta == 0) {
            return priorCustomer;
        }

        if (timeDelta < 0) {
            return -1;
        }
        return 1;
    }
}
