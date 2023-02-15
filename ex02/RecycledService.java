class RecycledService extends Service { 
    private static final int extraTime = 60;

    RecycledService(Loader loader, Cruise cruise) {
        super(loader, cruise);
    }

    @Override
    public int getServiceEndTime() {
        return super.getServiceEndTime() + extraTime;
    }

}
