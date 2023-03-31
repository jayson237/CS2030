import java.util.Random;
import java.util.function.Supplier;

class Main4 {
    private static final Random RNG_REST = new Random(3L);
    private static final Random RNG_REST_PERIOD = new Random(4L);
    private static final double SERVER_REST_RATE = 0.1;

    static double genRestPeriod() {
        return -Math.log(RNG_REST_PERIOD.nextDouble()) / SERVER_REST_RATE;
    }

    public static void main(String[] args) {
        //Scanner sc = new Scanner(System.in);
        ImList<Double> arrivalTimes = new ImList<Double>()
                .add(0.0)
                .add(0.025)
                .add(0.046)
                .add(0.124)
                .add(0.126);
        Supplier<Double> serviceTimes = () -> 1.0;
        int numOfServers = 0;
        int numOfSelfChecks = 2;
        int qmax = 2;
        double probRest = 0.5;
        Supplier<Double> restTimes = () ->
                RNG_REST.nextDouble() < probRest ? genRestPeriod() : 0.0;


        Simulator sim = new Simulator(numOfServers, numOfSelfChecks, qmax, arrivalTimes, serviceTimes, restTimes);
        System.out.println(sim.simulate());
        //sc.close();
    }
}
