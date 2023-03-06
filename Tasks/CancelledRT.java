class CancelledRT extends Manager {
    
    CancelledRT(int day, int start, int end, String desc) { 
        super(day, start, end, desc);
    }

    @Override
    public String toString() {
        return String.format("Recurring %s[cancelled]", super.toString());
    }
}
