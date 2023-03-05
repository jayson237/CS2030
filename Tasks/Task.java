class Task {
    private final int day;
    private final int start;
    private final int end;
    private final String desc;

    Task(int day, int start, int end, String desc) {
        this.day = day;
        this.start = start;
        this.end = end;
        this.desc = desc;
    }

    Task edit(int start, int end) {
        return new Task(day, start, end, desc);
    }
    
    CancelledTask cancel() {
         return new CancelledTask(day, start, end, desc); 
    }

    @Override
    public String toString() {
        return String.format("Task : %d, %d, %d, %s", day, start, end, desc);
    }
}
