import java.util.List;

class NormalCab extends Driver {
    private static final String type = "NormalCab";
    private static final ImList<Service> services = 
        new ImList<Service>(List.of(new JustRide(), new TakeACab()));

    NormalCab(String id, int waitTime) {
        super(id, waitTime, type, services);
    }
}
