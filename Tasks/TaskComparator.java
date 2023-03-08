import java.util.Comparator;

class TaskComparator implements Comparator<Manager> {
    public int compare(Manager m1, Manager m2) {
        int timeDelta = m1.getStart() - m2.getStart();
        int dayDelta = m1.getDay() - m2.getDay();
        if (dayDelta == 0) {
            return timeDelta;
        }

        if (dayDelta < 0) {
            return -1;
        }
        return 1;
    }
}
