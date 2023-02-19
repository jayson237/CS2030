class TimeConverter {
    private static final int minsInHour = 60;
    private static final int hundred = 100;

    TimeConverter() {
    }

    public int hourToMin(int time) {
        return ((time / hundred) * minsInHour + time % hundred);
    }
}
