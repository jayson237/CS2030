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

    private PQ<Event> initEvents() {
        PQ<Event> eventTemp = new PQ<Event>(new EventComparator());
        Server tempServer = new Server(0.0, -1, this.qmax, 0);
        for (int i = 0; i < this.arrival.size(); i++) {
            double arrTime = this.arrival.get(i);
            Customer customer = this.customers.get(i);
            eventTemp = eventTemp.add(new Arrive(arrTime, customer, tempServer));
        }
        return eventTemp;
    }

    private ImList<Server> initServers(int numOfServers) {
        ImList<Server> serverList = new ImList<Server>();
        for (int i = 0; i < numOfServers; i++) {
            serverList = serverList.add(new Server(0.0, i + 1, this.qmax, 0));
        }
        return serverList;
    }

    private ImList<Server> updateServerList(ImList<Server> serverList,
        Server oldServer, Server updatedServer) {
        ImList<Server> updatedList = new ImList<Server>();
        for (int i = 0; i < serverList.size(); i++) {
            if (i == serverList.indexOf(oldServer)) {
                updatedList = updatedList.add(updatedServer);
            } else {
                updatedList = updatedList.add(serverList.get(i));
            }
        }
        return updatedList;
    }

    private Pair<Server, ImList<Server>> findAvailableServer(double time, ImList<Server> servers) {
        for (int i = 0; i < numOfServers; i++) {
            if (servers.get(i).isAvailable(time)) {
                return new Pair<>(servers.get(i), servers);
            }
        }
        for (int j = 0; j < numOfServers; j++) {
            if (servers.get(j).getNumPeopleInQ() <= servers.get(j).getQMax()) {
                Server updated = servers.get(j).incrementQueue();
                System.out.println(updated.getNumPeopleInQ());
                servers = this.updateServerList(servers, servers.get(j), updated);
                return new Pair<>(updated, servers);
            }
        }
        return new Pair<>(new Server(-1,-1, -1, -1), servers);
    }

    public String simulate() {
        String output = "";
        int servedCustomer = 0;
        PQ<Event> events = this.initEvents();
        ImList<Server> serverList = this.initServers(this.numOfServers);
        while (!events.isEmpty()) {
            Pair<Event, PQ<Event>> eventPair = events.poll();
            Event currEvent = eventPair.first();
            int event = currEvent.getNum();
            Server availServer = currEvent.getServer();
            Pair<Server, ImList<Server>> serverPair = this.findAvailableServer(currEvent.getTime(), serverList);
            availServer = serverPair.first();
            serverList = serverPair.second();
            if (event == 1 && availServer.getServerId() != -1 && currEvent.getServer().getNumPeopleInQ() == 0) {
                servedCustomer++; // Directly Serves
                Server updatedServer = availServer.update(currEvent.getCustomer());
                serverList = this.updateServerList(serverList, availServer, updatedServer);
            } else if (event == 2 && currEvent.getServer().getNumPeopleInQ() > 0) { // Wait Serve
                Server updatedServer = availServer.decrementQueue(currEvent.getCustomer());
                serverList = this.updateServerList(serverList, availServer, updatedServer);
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

