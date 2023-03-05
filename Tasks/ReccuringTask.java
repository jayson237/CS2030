class ReccuringTask {
    private final Task task;
    private final int freq;
    private final int occur;

    ReccuringTask(Task task, int freq, int occur) {
        this.task = task;
        this.freq = freq;
        this.occur = occur;
    }

    Task edit(int cancelIndex, int day, int start, int end) {

    }

    CancelledRT cancel() {
        return new CancelledRT(task, freq, occur);
    }

    CancelledRT cancel(int cancelIndex) {


    }

    @Override
    public String toString() { 
        return String.format("Reccuring %s", task);
    }
}
