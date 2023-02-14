public class Service {
    private final Loader loader;
    private final Cruise cruise;

    Service(Loader loader, Cruise cruise) {
        this.loader = loader;
        this.cruise = cruise;
    }

    public int getServiceEndTime() {
        return cruise.getArrivalTime() + cruise.getServiceTime();
    }

    public int getServiceStartTime() {
        return cruise.getArrivalTime();
    }

    public int getLoaderNum() {
        return this.loader.getIdentifier();
    }

    @Override
    public String toString() {
        return String.format("%s serving %s", this.loader.toString(), this.cruise.toString());
    }
}
