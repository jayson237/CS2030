class Arrive extends Event {
    private final int qmax;

    Arrive(double time, Customer customer, int qmax) {
        super(time, customer);
        this.qmax = qmax;
    }

    public double getArrivalTime() {
        return super.getCustomer().getArrivalTime();
    }

    public Customer getCustomer() {
        return super.getCustomer();
    }

    private Server getAvailableServer(ImList<Server> serverList, Customer customer) {
        for (int i = 0; i < serverList.size(); i++) {
            if (serverList.get(i).isAvailable(customer)) {
                return serverList.get(i);
            }
        }
        return new Server(-1, -1, new ImList<Wait>(), qmax);
    }

    private Server getAvailableQueue(ImList<Server> serverList) {
        for(int i = 0; i < serverList.size(); i++) {
            if (serverList.get(i).isQueueAvailable()) {
                return serverList.get(i);
            }
        }
        return new Server(-1,-1, new ImList<Wait>(), qmax);
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

    private ImList<Wait> updateQueueList(ImList<Wait> queue, Server server) {
        ImList<Wait> newQueue = new ImList<Wait>();
        if (!server.isAvailable(this.getCustomer())) {
            newQueue = queue.add(new Wait(this.getArrivalTime(), this.getCustomer(), server));
        } else {
            newQueue = queue.remove(0);
        }
        return newQueue;
    }

    @Override
    public Pair<Event, ImList<Server>> execute(ImList<Server> serverList) {
        double time = this.getArrivalTime();
        Customer customer = this.getCustomer();
        Server availableServer = this.getAvailableServer(serverList, customer);
        Server availableQueue = this.getAvailableQueue(serverList);
        if (availableServer.getServerId() != -1) {
            Server newServer = availableServer.updateServer(customer);
            serverList = this.updateServerList(serverList, availableServer, newServer);
            return new Pair<>(new Serve(customer.getArrivalTime(),
                customer, availableServer), serverList);
        } else if (availableServer.getServerId() == -1 && availableQueue.getServerId() != -1) {
            ImList<Wait> newQueue = this.updateQueueList(availableQueue.getQueue(), availableQueue);
            Server newAvailableQueue = availableQueue.updateQueue(newQueue);
            serverList = this.updateServerList(serverList, availableQueue, newAvailableQueue);
            return new Pair<>(new Wait(time, customer, availableQueue), serverList);
        } else {
            return new Pair<>(new Leave(time, customer), serverList);
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s arrives\n", super.toString(), this.getCustomer().toString());
    }
}
