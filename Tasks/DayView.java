class DayView implements View {
    
    DayView(int day) {
        this.day = day;
    }

    void view(ImList<Task> tasks) {
        for (Task t : tasks) {
            if (!cancelled) {
                System.out.println(t);
            }
        }
    }
}
