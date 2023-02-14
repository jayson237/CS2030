class BigCruise extends Cruise {
    private static final int loaderPer40Meter = 40;
    private static final int servePassengers = 50;

    BigCruise(String id, int arrive, int length, int numOfPassengers) {
        super(id, arrive,
            (int)Math.ceil((double) length / loaderPer40Meter),
            (int)Math.ceil((double) numOfPassengers / servePassengers));
    }
}
