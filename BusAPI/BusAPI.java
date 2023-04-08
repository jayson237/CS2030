import java.io.IOException;
import java.lang.InterruptedException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletableFuture;

/**
 * The BusAPI class interface with the Web API running at 
 * cs2030-bus-api.herokuapp.com to retrieve:
 * - bus services that serve a given bus stop 
 * - bus stop visited by a bus service.
 *
 * @author: cs2030 (orig. Ooi Wei Tsang)
 **/
class BusAPI {
    /** HTTP request succeeded. */
    private static final int HTTP_RESPONSE_STATUS_OK = 200;

    /** URL to query for bus stops. */
    private static final String BUS_SERVICE_API = 
        "https://cs2030-bus-api.herokuapp.com/bus_services/";

    /** URL to query for bus services. */
    private static final String BUS_STOP_API = 
        "https://cs2030-bus-api.herokuapp.com/bus_stops/";

    /**
     * Given a URL, synchrnously obtained the HTTP response string
     * from the URL.  It returns an emptry string and prints an
     * error message if the URL is invalid (not the best behavior).
     * @param url The URL to query
     * @return The HTTP resposne body, or an empty string if the 
     *     query fails.
     */
    private static CompletableFuture<String> httpGet(String url) {
        HttpClient client = HttpClient.newBuilder()
            .build();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();
        CompletableFuture<HttpResponse<String>> response;

        response = client.sendAsync(request, BodyHandlers.ofString());
        return response.thenApply(r -> {
            if (r.statusCode() != HTTP_RESPONSE_STATUS_OK) {
                System.out.println(r + " " + r.statusCode());
                return "";
            }
            return r.body();
        });
    }

    /**
     * Return the string from CS2030 BUS API given a bus service ID.
     * @param serviceId Bus service id
     * @return The string returned by the CS2030 BUS API service listing
     *     the bus stops that are serviced at this bus.  Return an empty
     *     string if something go wrong.
     */ 
    public static CompletableFuture<String> getBusStopsServedBy(String serviceId) {
        return httpGet(BUS_SERVICE_API + serviceId);
    }

    /**
     * Return the string from CS2030 BUS API given a bus stop ID.
     * @param stopId Bus stop id
     * @return The string returned by the CS2030 BUS API service listing
     *     the bus services that stopped at this bus stop; an empty 
     *     string if the API query failed.
     */ 
    public static CompletableFuture<String> getBusServicesAt(String stopId) {
        return httpGet(BUS_STOP_API + stopId);
    }
}
