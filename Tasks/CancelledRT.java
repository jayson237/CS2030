class CancelledRT extends ReccuringTask {

    CancelledRT(Task task, int freq, int occur) {
        super(task, freq, occur);
    }

    @Override
    public String toString() {
        return String.format("%s[cancelled]", super.toString());
    }
}
