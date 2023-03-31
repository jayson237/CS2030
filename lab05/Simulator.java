import java.util.function.Supplier;

class Simulator {
    private final int numOfServers;
    private final int numOfSelfChecks;
    private final int qmax;
    private final ImList<Double> arrival;
    private final ImList<Customer> customers;
    private final Supplier<Double> restTimes;

    Simulator(int numOfServers, int numOfSelfChecks, int qmax, ImList<Double> arrTime,
              Supplier<Double> servTime, Supplier<Double> restTimes) {
        this.numOfServers = numOfServers;
        this.numOfSelfChecks = numOfSelfChecks;
        this.qmax = qmax;
        this.arrival = arrTime;
        this.restTimes =  restTimes;
        ImList<Customer> customers = new ImList<Customer>();
        for (int i = 0; i < this.arrival.size(); i++) {
            double arrivalTime = this.arrival.get(i);
            customers = customers.add(new Customer(i + 1, arrivalTime, servTime));
        }
        this.customers = customers;
    }

    private ImList<Server> initServers() {
        ImList<Server> serverTemp = new ImList<Server>();
        for (int i = 0; i < numOfServers; i++) {
            serverTemp = serverTemp.add(new Server(0.0, i + 1));
        }
        if (numOfSelfChecks > 0) {
            int counters = numOfSelfChecks;
            int counterId = numOfServers + 1;
            while (counters > 0) {
                serverTemp = serverTemp.add(new SelfCheck(0.0, counterId, new ImList<Customer>()));
                counters -= 1;
                counterId += 1;
            }
        }
        return serverTemp;
    }

    private int findAvailableServer(double time, ImList<Server> servers) {
        for (int i = 0; i < numOfServers + numOfSelfChecks; i++) {
            if (servers.get(i).isAvailable(time)) {
                return i + 1;
            }
        }
        return -1;
    }

    private int findAvailableQueue(ImList<Server> servers) {
        int queues = numOfServers;
        if (numOfSelfChecks > 0) {
            queues += 1;
        }
        for (int i = 0; i < queues; i++) {
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
        double waitTime = 0;
        PQ<Event> events = this.initEvents();
        ImList<Server> serverList = this.initServers();
        while (!events.isEmpty()) {
            Pair<Event, PQ<Event>> eventPair = events.poll();
            Event currEvent = eventPair.first();
            int availServer = this.findAvailableServer(currEvent.getTime(), serverList);
            int availQueue = this.findAvailableQueue(serverList);
            Pair<Event, ImList<Server>> nextEventPair = currEvent
                    .execute(numOfServers, availServer, availQueue, serverList, restTimes);
            Event nextEvent = nextEventPair.first();
            serverList = nextEventPair.second();
            events = eventPair.second();
            if (nextEvent != currEvent) {
                events = events.add(nextEvent);
                servedCustomer += currEvent.countServed();
                waitTime += currEvent.countWaitTime(serverList, numOfServers);
            }
            output += currEvent.toString();
        }
        int notServed = this.arrival.size() - servedCustomer;
        double waitAverage = waitTime / servedCustomer;
        if (servedCustomer == 0) {
            output += String.format("[%.3f %d %d]", 0.000, servedCustomer, notServed);
        } else {
            output += String.format("[%.3f %d %d]", waitAverage, servedCustomer, notServed);
        }
        return output;
    }
}

