import java.util.function.Supplier;

class Simulator {
    private final int numOfServers;
    private final int qmax;
    private final ImList<Double> arrival;
    private final ImList<Customer> customers;

    Simulator(int numOfServers, int qmax, ImList<Double> arrTime, Supplier<Double> servTime) {
        this.numOfServers = numOfServers;
        this.arrival = arrTime;
        this.qmax = qmax;
        ImList<Customer> customers = new ImList<Customer>();
        for (int i = 0; i < this.arrival.size(); i++) {
            double arrivalTime = this.arrival.get(i);
            customers = customers.add(new Customer(i + 1, arrivalTime, servTime));
        }
        this.customers = customers;
    }

    private ImList<Server> initServers(int numOfServers) {
        ImList<Server> serverTemp = new ImList<Server>();
        for (int i = 0; i < numOfServers; i++) {
            serverTemp = serverTemp.add(new Server(0.0, i + 1));
        }
        return serverTemp;
    }

    private int findAvailableServer(double time, ImList<Server> servers) {
        for (int i = 0; i < numOfServers; i++) {
            if (servers.get(i).isAvailable(time)) {
                return i + 1;
            }
        }
        return -1;
    }

    private int findAvailableQueue(ImList<Server> servers) {
        for (int i = 0; i < numOfServers; i++) {
            if (servers.get(i).getQSize() < qmax) {
                return i + 1;
            }
        }
        return -1;
    }

    private PQ<Event> initEvents() {
        PQ<Event> eventTemp = new PQ<Event>(new EventComparator());
        Server tempServer = new Server(-1, -1);
        for (int i = 0; i < this.arrival.size(); i++) {
            double arrTime = this.arrival.get(i);
            Customer customer = this.customers.get(i);
            eventTemp = eventTemp.add(new Arrive(arrTime, customer, tempServer));
        }
        return eventTemp;
    }

    public String simulate() {
        String output = "";
        int servedCustomer = 0;
        PQ<Event> events = this.initEvents();
        ImList<Server> serverList = this.initServers(this.numOfServers);
        while (!events.isEmpty()) {
            Pair<Event, PQ<Event>> eventPair = events.poll();
            Event currEvent = eventPair.first();
            output += currEvent.toString();
            events = eventPair.second();
            int availServer = this.findAvailableServer(currEvent.getTime(), serverList);
            int availQueue = this.findAvailableQueue(serverList);
            Pair<Event, ImList<Server>> nextEventPair = currEvent
                    .execute(availServer, availQueue, serverList);
            Event nextEvent = nextEventPair.first();
            ImList<Server> updatedList = nextEventPair.second();
            serverList = updatedList;
            if (nextEvent != currEvent) {
                events = events.add(nextEvent);
                servedCustomer += nextEvent.countServed();
            }
        }
        int notServed = this.arrival.size() - servedCustomer;
        String stats = String.format("[%d %d]", servedCustomer, notServed);
        return output + stats;
    }
}

