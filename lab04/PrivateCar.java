import java.util.List;

class PrivateCar extends Driver {
    private static final String type = "PrivateCar";
    private static final ImList<Service> services = 
        new ImList<Service>(List.of(new JustRide(), new ShareARide()));

    PrivateCar(String id, int waitTime) {
        super(id, waitTime, type, services);
    }
}
