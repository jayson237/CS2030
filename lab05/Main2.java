import java.util.Random;
import java.util.function.Supplier;

class Main2 {
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
                .add(0.313608)
                .add(1.204910)
                .add(2.776499)
                .add(3.876961)
                .add(3.909737)
                .add(9.006391)
                .add(9.043361)
                .add(9.105379)
                .add(9.159630);
        Supplier<Double> serviceTimes = () -> 1.0;
        int numOfServers = 1;
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
