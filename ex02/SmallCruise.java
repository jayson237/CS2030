class SmallCruise extends Cruise {
    private static final int numOfLoaders = 1;
    private static final int serviceTime = 30;

    SmallCruise(String id, int arrTime) {
        super(id, arrTime, numOfLoaders, serviceTime);
    }
    
    @Override
    public int getArrivalTime() {
        return super.getArrivalTime();
    }

    @Override
    public int getServiceTime() {
        return super.getServiceTime();
    }

    @Override
    public int getNumOfLoadersRequired() {
        return super.getNumOfLoadersRequired();
    }
}
