import java.util.function.Supplier;

class Arrive extends Event {

    Arrive(double time, Customer customer, Server server) {
        super(time, customer, server);
    }

    @Override
    Pair<Event, ImList<Server>> execute(int numOfServers, int server, int queue,
                                        ImList<Server> servers,
                                        Supplier<Double> restTimes) {
        if (server != -1 && server <= numOfServers) {
            return new Pair<>(new Serve(super.getTime(), super.getCustomer(),
                    new Server(super.getIsUsedUntil(), server), false), servers);
        } else if (server != -1) {
            return new Pair<>(new Serve(super.getTime(), super.getCustomer(),
                    new SelfCheck(super.getIsUsedUntil(), server), false), servers);
        } else if (queue != -1 && queue <= numOfServers) {
            Server currServer = servers.get(queue - 1);
            servers = servers.set(queue - 1, new Server(currServer.getIsUsedUntil(),
                    queue, currServer.getQ().add(super.getCustomer())));
            return new Pair<>(new Wait(super.getTime(), super.getCustomer(),
                    servers.get(queue - 1), false, false), servers);
        } else if (queue != -1) {
            Server expressLane = servers.get(queue - 1);
            servers = servers.set(queue - 1, new SelfCheck(expressLane.getIsUsedUntil(),
                    queue, expressLane.getQ().add(super.getCustomer())));
            return new Pair<>(new Wait(super.getTime(), super.getCustomer(),
                    servers.get(queue - 1), false, true), servers);
        }
        return new Pair<>(new Leave(super.getTime(), super.getCustomer(),
                super.getServer()), servers);
    }

    @Override
    public String toString() {
        return String.format("%s %d arrives\n", super.toString(), super.getCustomerId());
    }
}
