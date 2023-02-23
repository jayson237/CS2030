class Simulator {
    private final int numOfServers;
    private final ImList<Double> arrival;
    private final ImList<Customer> customers;

    Simulator(int numOfServers, ImList<Double> arrTime, ImList<Double> servTime) {
        this.numOfServers = numOfServers;
        this.arrival = arrTime;
        ImList<Customer> customers = new ImList<Customer>();
        for (int i = 0; i < this.arrival.size(); i++) {
            double arrivalTime = this.arrival.get(i);
            double serviceTime = servTime.get(i);
            customers = customers.add(new Customer(i + 1, arrivalTime, serviceTime));
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

    private ImList<Server> updateServerList(ImList<Server> serverTemp, 
        Server oldServer, Server updatedServer) {
        ImList<Server> updatedList = new ImList<Server>();
        for (int i = 0; i < serverTemp.size(); i++) {
            if (i == serverTemp.indexOf(oldServer)) {
                updatedList = updatedList.add(updatedServer);
            } else {
                updatedList = updatedList.add(serverTemp.get(i));
            }
        }
        return updatedList;
    }

    public Server findAvailableServer(double time, ImList<Server> servers) {
        for (int i = 0; i < numOfServers; i++) {
            if (servers.get(i).isAvailable(time)) {
                return servers.get(i);
            }
        }
        return new Server(-1, -1);
    }

    private PQ<Event> initEvents() {
        PQ<Event> eventTemp = new PQ<Event>(new EventComparator());
        Server tempServer = new Server(0.0, -1);
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
        ImList<Server> serverTemp = this.initServers(this.numOfServers);
        while (!events.isEmpty()) {
            Pair<Event, PQ<Event>> eventPair = events.poll();
            Event currEvent = eventPair.first();
            Server availServer = this.findAvailableServer(currEvent.getTime(), serverTemp);
            if (currEvent.getType() == 1) {
                if (availServer.getServerId() != -1) {
                    servedCustomer++;
                    Server newServer = availServer.update(currEvent.getCustomer());
                    serverTemp = this.updateServerList(serverTemp, availServer, newServer);
                } 
            }
            events = eventPair.second();
            output += currEvent.toString();
            if (currEvent.execute(availServer.getServerId()) != currEvent) {
                events = events.add(currEvent.execute(availServer.getServerId()));
            }
        }
        int notServedCustomer = this.arrival.size() - servedCustomer;
        String counter = String.format("[%d %d]", servedCustomer, notServedCustomer);
        return output + counter;
    }
}

