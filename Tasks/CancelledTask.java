class CancelledTask extends Task {
     
    CancelledTask(int day, int start, int end, String desc) {
        super(day, start, end, desc);
    }

    @Override
    public String toString() {
        return String.format("%s[cancelled]", super.toString());
    }
}
