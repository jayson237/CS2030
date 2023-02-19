abstract class Driver {
    private final String id;
    private final int waitTime;
    private final String type;
    private final ImList<Service> services;

    Driver(String id, int waitTime, String type, ImList<Service> services) {
        this.id = id;
        this.waitTime = waitTime;
        this.type = type;
        this.services = services;
    }

    public ImList<Service> getServices() {
        return this.services;
    }

    public int shorterWaitTime(Driver other) {
        if (this.waitTime == other.waitTime) {
            return 0;
        } else if (this.waitTime > other.waitTime) {
            return 1;
        }
        return -1;
    }


    @Override
    public String toString() { 
        return String.format("%s (%d mins away) %s", this.id, this.waitTime, this.type);
    }
}
