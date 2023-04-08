import java.util.Scanner;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * BusService encapsulate a bus service with a String id.  It supports
 * querying for the list of bus stops served by this service.
 *
 * @author: cs2030 (orig. Ooi Wei Tsang)
 */
class BusService implements Comparable<BusService> {
    private final String serviceId;

    /**
     * Construct a BusService object with a given id and an empty
     * list of bus stops.
     * @param id The id of this bus service.
     */
    public BusService(String id) {
        this.serviceId = id;
    }

    /**
     * Get the current list of bus stops.  Query the web server
     * if bus stops are not retrieved before.
     * @return A list of bus stops that this bus services serves.
     */
    public CompletableFuture<List<BusStop>> getBusStops() {
        return BusAPI.getBusStopsServedBy(serviceId)
            .thenApply(busStops -> {
                Scanner sc = new Scanner(busStops);
                List<BusStop> stops = sc.useDelimiter("\n")
                    .tokens()
                    .map(line -> line.split(","))
                    .map(fields -> new BusStop(fields[0], fields[1]))
                    .toList();
                sc.close();
                return stops;
            });
    }

    /**
     * Returns a list of bus stops matching a given name.
     * @param  name Name (possibly partial) of a bus stop.
     * @return A list of bus stops matching the given name.
     */
    public CompletableFuture<List<BusStop>> findStopsWith(String name) {
        return getBusStops()
            .thenApply(listBusStops -> listBusStops
                .stream()
                .filter(stop -> stop.matchName(name))
                .toList());
    }

    /**
     * Return true if this bus service is equals to another bus service.
     * Two bus services are equal if they have the same id.
     * @param  obj another bus service to check for equality.
     * @return true if the bus servives are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof BusService busService) {
            return this.serviceId.equals(busService.serviceId);
        } else {
            return false;
        }
    }

    public int compareTo(BusService other) {
        return this.serviceId.compareTo(other.serviceId);
    }

    /**
     * Return the hash code of this bus service.
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return serviceId.hashCode();
    }

    /**
     * Convert this bus service to a string.
     * @return A string containing the id of this bus service.
     */
    @Override
    public String toString() {
        return serviceId;
    }
}
