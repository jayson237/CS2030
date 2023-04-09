import java.time.Instant;
import java.util.List;
import java.time.Duration;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

class Main {

    static CompletableFuture<BusRoutes> processQuery(String query) {
        Scanner sc = new Scanner(query);
        BusStop srcBusStop = new BusStop(Integer.valueOf(sc.next()).toString());
        String searchString = sc.next();
        sc.close();
        return BusSg.findBusServicesBetween(srcBusStop, searchString);
    }
    
    public static void main(String[] args) {
        Instant start = Instant.now();
        Scanner sc = new Scanner(System.in);
        List<CompletableFuture<BusRoutes>> result = sc.useDelimiter("\n").tokens()
            .map(s -> processQuery(s))
            .toList();
        result.stream()
            .forEach(x -> System.out.println(x.thenCompose(y -> y.description())
                    .join()));
        Instant stop = Instant.now();
        System.out.printf("Took %,dms\n", Duration.between(start, stop).toMillis());
    }

    /*
    public static void main(String[] args) {
        Instant start = Instant.now();
        Scanner sc = new Scanner(System.in);
        List<CompletableFuture<BusRoutes>> result = sc.useDelimiter("\n").tokens()
            .map(s -> processQuery(s))
            .toList();
        result.stream()
            .forEach(x -> System.out.println(x.join().description().join()));
        Instant stop = Instant.now();
        System.out.printf("Took %,dms\n", Duration.between(start, stop).toMillis());
    }
    */
}
