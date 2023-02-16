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
        ImList<Server> serverTemp = this.initServers(this.numOfServers);
        for (int i = 0; i < this.arrival.size(); i++) {
            Server tempServer = new Server(0.0, -1);
            double arrTime = this.arrival.get(i);
            Customer customer = this.customers.get(i);
            Server available = this.findAvailableServer(arrTime, serverTemp);
            if (available.getServerId() != -1) {
                Server newServer = available.update(customer);
                serverTemp = this.updateServerList(serverTemp, available, newServer);
                tempServer = available;
            }
            eventTemp = eventTemp.add(new Arrive(arrTime, customer, tempServer));
        }
        return eventTemp;
    }

    public String counter() {
        int servedCustomer = 0;
        ImList<Server> serverTemp = this.initServers(this.numOfServers);
        for (int i = 0; i < this.arrival.size(); i++) {
            Customer customer = this.customers.get(i);
            double arrivalTime = customer.getArrival();
            Server availServer = this.findAvailableServer(arrivalTime, serverTemp);
            if (availServer.getServerId() != -1) {
                servedCustomer++;
                Server newServer = availServer.update(customer);
                serverTemp = this.updateServerList(serverTemp, availServer, newServer);
            }
        }
        int notServedCustomer = this.arrival.size() - servedCustomer;
        return String.format("[%d %d]", servedCustomer, notServedCustomer);
    }


    public String simulate() {
        String output = "";
        PQ<Event> events = this.initEvents();
        while (!events.isEmpty()) {
            Pair<Event, PQ<Event>> eventPair = events.poll();
            Event currEvent = eventPair.first();
            events = eventPair.second();
            output += currEvent.toString();
            if (currEvent.execute() != currEvent) {
                events = events.add(currEvent.execute());
            }
        }
        return output + this.counter();
    }
}

