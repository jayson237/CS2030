abstract class Service {
    private static final int peakHourStart = 360;
    private static final int peakHourEnd = 540;
    private static final TimeConverter converter = new TimeConverter();
    
    Service() {
    }
    
    public int computeFare(int distance, int numOfPass, int tos) {
        return 0;
    }
    
    protected boolean isPeakHourRide(int tos) {
        int tosInMins = converter.hourToMin(tos);
        if (tosInMins >= peakHourStart && tosInMins <= peakHourEnd) {
            return true;
        }
        return false;
    }

}
