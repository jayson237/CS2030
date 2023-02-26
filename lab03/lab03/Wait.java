class Wait extends Event {
	private final Server server;

	Wait(double time, Customer customer, Server server) {
		super(time, customer);
		this.server = server;
	}

	public double getArrivalTime() {
        return super.getArrivalTime();
    }

	public Customer getCustomer() {
		return super.getCustomer();
	}

	@Override
	public Pair<Event, ImList<Server>> execute(ImList<Server> serverList) {
		Server updated = new Server(this.server.getServerId(),
				this.server.getEndTime() + this.getCustomer().getServiceTime(), this.server.getQueue(), this.server.getQMax());
		//serverList = this.updateServerList(serverList, this.server, updated);
        return new Pair<>(new Serve(this.server.getEndTime(), this.getCustomer(), updated), serverList);
    }

	@Override
	public double getAverageWaiting() {
		return this.server.getEndTime() - this.getCustomer().getArrivalTime();
	}

	@Override
	public int getWaitEvent() {
		return 1;
	}

	@Override
	public String toString() {
		return String.format("%s %s waits at %s\n",
				super.toString(), this.getCustomer().toString(), this.server.toString());
	}
}