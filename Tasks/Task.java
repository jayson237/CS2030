class Task extends Manager {

    Task(int day, int start, int end, String desc) {
        super(day, start, end, desc);
    }

    Task edit(int start, int end) {
        return new Task(getDay(), start, end, getDesc());
    }

    CancelledTask cancel() {
        return new CancelledTask(getDay(), getStart(), getEnd(), getDesc());
    }
 
    @Override
    public String toString() {
        return String.format("Task: %d,%d,%d,%s", getDay(), getStart(), getEnd(), getDesc());
    }
}
