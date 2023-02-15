import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ImList<Double> arrivalTimes = new ImList<Double>();
        ImList<Double> serviceTimes = new ImList<Double>();

        int numOfServers = sc.nextInt();
        while (sc.hasNextDouble()) {
            arrivalTimes = arrivalTimes.add(sc.nextDouble());
            serviceTimes = serviceTimes.add(sc.nextDouble());
        }

        Simulator sim = new Simulator(numOfServers, arrivalTimes, serviceTimes);
        System.out.println(sim.simulate());
        sc.close();
    }
}