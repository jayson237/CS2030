class Manager { 
    private final int day;
    private final int start;
    private final int end;
    private final String desc;
    
    Manager(int day, int start, int end, String desc) {
        this.day = day;
        this.start = start;
        this.end = end;
        this.desc = desc;
    }

    int getDay() {
        return this.day;
    }

    int getStart() {
        return this.start;
    }

    int getEnd() {
        return this.end;
    }

    String getDesc() {
        return this.desc;
    }

    @Override
    public String toString() {
        return String.format("Task: %d,%d,%d,%s", day, start, end, desc);
    }
}
