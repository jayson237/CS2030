import java.util.function.Supplier;

class Main {
    public static void main(String[] args) {
        /*
        Scanner sc = new Scanner(System.in);
        ImList<Double> arrivalTimes = new ImList<Double>();
        Supplier<Double> serviceTime = new DefaultServiceTime();

        int numOfServers = sc.nextInt();
        int qmax = sc.nextInt();
        while (sc.hasNextDouble()) {
            arrivalTimes = arrivalTimes.add(sc.nextDouble());
        }
         */
        ImList<Double> arrivalTimes = new ImList<Double>().add(0.5).add(0.6).add(0.7);
        Supplier<Double> serviceTime = new DefaultServiceTime();
        Simulator sim = new Simulator(1, 1, arrivalTimes, serviceTime);
        System.out.println(sim.simulate());
        //sc.close();
    }
}
