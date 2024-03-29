class CancelledTask extends Manager {

    CancelledTask(int day, int start, int end, String desc) {
        super(day, start, end, desc);
    }

    @Override
    boolean isCancelled() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s[cancelled]", super.toString());
    }
}
