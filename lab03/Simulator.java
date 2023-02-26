import java.util.function.Supplier;

class Simulator {
    private final int numOfServers;
    private final int qmax;
    private final ImList<Double> arrivalTimes;
    private final ImList<Customer> customers;

    Simulator(int numOfServers, int qmax, ImList<Double> arrivalTimes, 
            Supplier<Double> serviceTime) {
        this.numOfServers = numOfServers;
        this.qmax = qmax;
        this.arrivalTimes = arrivalTimes;
        ImList<Customer> customers = new ImList<Customer>();
        for (int i = 0; i < this.arrivalTimes.size(); i++) {
            double arrivalTime = this.arrivalTimes.get(i);
            customers = customers.add(new Customer(i + 1, arrivalTime, serviceTime));
        }
        this.customers = customers;
    }

    private ImList<Server> initServers(int numOfServers) {
        ImList<Server> serverList = new ImList<Server>();
        for (int i = 0; i < numOfServers; i++) {
            serverList = serverList.add(new Server((i + 1), 0, new ImList<Customer>(), this.qmax));
        }
        return serverList;
    }

    private PQ<Event> initEvents() {
        PQ<Event> pq = new PQ<Event>(new EventComparator());
        for (int i = 0; i < customers.size(); i++) {
            Customer currCustomer = customers.get(i);
            double currArrivalTime = currCustomer.getArrivalTime();
            Arrive arrive = new Arrive(currArrivalTime, currCustomer, this.qmax);
            pq = pq.add(arrive);
        }
        return pq;
    }

    public String simulate() {
        String output = "";
        int servedCustomer = 0;
        int waitEvent = 0;
        double averageWaiting = 0;
        PQ<Event> pq = this.initEvents();
        ImList<Server> serverList = this.initServers(this.numOfServers);
        Pair<Event, PQ<Event>> pr = pq.poll();
        while (!pq.isEmpty()) {
            pq = pr.second();
            Event currentEvent = pr.first();
            Pair<Event, ImList<Server>> eventPair = currentEvent.execute(serverList);
            averageWaiting += currentEvent.getAverageWaiting();
            servedCustomer += currentEvent.getStatistics();
            waitEvent += currentEvent.getWaitEvent();
            Event nextEvent = eventPair.first();
            serverList = eventPair.second();
            if (currentEvent != nextEvent) {
                pq = pq.add(nextEvent);
            }
            output += currentEvent;
            pr = pq.poll();
        }
        averageWaiting /= (waitEvent + 1);
        int notServedCustomer = this.arrivalTimes.size() - servedCustomer;
        String counter = String.format("[%.3f %d %d]", 
                averageWaiting, servedCustomer,notServedCustomer);
        return output + counter;
    }
}
