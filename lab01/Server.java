class Server {
    private final int serverNo;
    private final double nextAvailTime;

    Server(int serverNo, double nextAvailTime) {
        this.serverNo = serverNo;
        this.nextAvailTime = nextAvailTime;
    }

    public int getServerNo() {
        return this.serverNo;
    }

    public double getNextAvailTime() {
        return this.nextAvailTime;
    }

    public boolean checkIsBusy(Customer otherCustomer) {
        return otherCustomer.getArrivalTime() < this.nextAvailTime; 
    }

    public Server updateServer(Customer otherCustomer) {
        return new Server(this.getServerNo(), otherCustomer.getArrivalTime() 
        + otherCustomer.getServiceTime());
    }

    @Override
    public String toString() {
        return "server " + serverNo;
    }
}