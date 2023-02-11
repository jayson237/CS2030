class Shop {
    private final int numOfServers;
    private final ImList<Customer> customerList;

    Shop(int numOfServers, ImList<Double> arrivalTimes, ImList<Double> serviceTimes) {
        this.numOfServers = numOfServers;
        ImList<Customer> customerList = new ImList<Customer>();
        for (int i = 0; i < arrivalTimes.size(); i++) {
            double currArrivalTime = arrivalTimes.get(i);
            double currServiceTime = serviceTimes.get(i);
            customerList = customerList.add(new Customer(i + 1, currArrivalTime, currServiceTime));
        }
        this.customerList = customerList;
    }

    private int getNumOfServers() {
        return this.numOfServers;
    }

    public ImList<Server> createServers() {
        ImList<Server> serverList = new ImList<Server>();
        for (int i = 0; i < this.getNumOfServers(); i++) {
            serverList = serverList.add(new Server(i + 1, 0));
        }
        return serverList;
    }

    public Server getAvailServer(ImList<Server> serverList, Customer customer) {
        for (int i = 0; i < serverList.size(); i++) {
            if (!serverList.get(i).checkIsBusy(customer)) {
                return serverList.get(i);
            }
        }
        return new Server(-1, -1);
    }

    public ImList<Server> updateServerList(ImList<Server> oldServerList,
        Server oldServer, Server newServer) {
        ImList<Server> newServerList = new ImList<Server>();
        for (int i = 0; i < oldServerList.size(); i++) {
            if (i == oldServerList.indexOf(oldServer)) {
                newServerList = newServerList.add(newServer);
            } else {
                newServerList = newServerList.add(oldServerList.get(i));
            }
        }
        return newServerList;
    }

    @Override
    public String toString() {
        String out = "";
        int numServed = 0;
        int numLeft = 0;
        ImList<Server> serverList = this.createServers();
        for (int i = 0; i < this.customerList.size(); i++) {
            Customer currCustomer = this.customerList.get(i);
            double currArrivalTime = currCustomer.getArrivalTime();
            out += new Arrive(currCustomer, currArrivalTime) + "\n";
            Server availServer = this.getAvailServer(serverList, currCustomer);
            if (availServer.getServerNo() != -1) {
                out += new Serve(availServer, currCustomer, currArrivalTime) + "\n";
                numServed += 1;
                Server newServer = availServer.updateServer(currCustomer);
                serverList = this.updateServerList(serverList, availServer, newServer);
            } else {
                out += new Leave(currCustomer, currArrivalTime) + "\n";
                numLeft += 1;
            }
        }
        return out + "[" + numServed + " " + numLeft + "]";
    }
}