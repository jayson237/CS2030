/**
 * Encapsulates a bus stop with a unique String id and human friendly name.
 *
 * @author: cs2030 (orig. Ooi Wei Tsang)
 */
class BusStop {
    /** A unique String id. */
    private final String stopId;

    /** A human friendly name. */
    private final String name;

    /**
     * Constructor for this bus stop.
     * @param   id     The id of this bus stop.
     * @param   name   A human friendly name of the bus stop.
     */
    public BusStop(String id, String name) {
        this.stopId = id;
        this.name = name;
    }

    /**
     * Constructor for this bus stop without name.
     * @param   id     The id of this bus stop.
     */
    public BusStop(String id) {
        this.stopId = id;
        this.name = "";
    }

    public String getStopId() {
        return this.stopId;
    }

    /**
     * Checks if the bus stop name matches the given string.
     * @param  name The string to match.
     * @return true if the name matches; false otherwise.
     */
    public boolean matchName(String name) {
        return this.name.toUpperCase().indexOf(name.toUpperCase()) != -1;
    }

    /**
     * Checks of this bus stop equals to another bus stop -- two bus
     * stops are equal if their id is the same.
     * @param  obj Another object to compare against.
     * @return  true if the two objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof BusStop other) {
            return this.stopId.equals(other.stopId);
        } else {
            return false;
        }
    }

    /**
     * Return a string representation of the bus stop.
     * @return Return the name of the bus stop.
     */
    @Override
    public String toString() {
        return stopId + (name.isEmpty() ? "" : " " + name);
    }
}
