class Cruise {
    private final String identifier;
    private final int arrival;
    private final int loaders;
    private final int service;
    private static final int hourToMinutes = 60;
    private static final int divider = 100;

    Cruise(String identifier, int arrival, int loaders, int service) {
        this.identifier = identifier;
        this.arrival = arrival;
        this.loaders = loaders;
        this.service = service;
    }

    public int getServiceTime() {
        return this.service;
    }

    public int getArrivalTime() {
        int hour = (int) this.arrival / divider;
        int minutes = this.arrival % divider;
        return hour * hourToMinutes + minutes;
    }

    public int getNumOfLoadersRequired() {
        return this.loaders;
    }

    @Override
    public String toString() {
        return String.format("%s@%04d", this.identifier, this.arrival);
    }

}
