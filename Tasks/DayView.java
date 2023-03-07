class DayView implements View {
    private final int day;

    DayView(int day) {
        this.day = day;
    }

    int getViewDay() {
        return this.day;
    }

    @Override
    public void view(ImList<Manager> tasks) {
        for (Manager t : tasks) {
            System.out.println(t);
        }
    }
}
