import java.util.function.Supplier;

class SelfCheck extends Server {

    SelfCheck(double isUsedUntil, int serverId, ImList<Customer> queue) {
        super(isUsedUntil, serverId, queue);
    }

    SelfCheck(double isUsedUntil, int serverId) {
        super(isUsedUntil, serverId);
    }

    @Override
    Server rest(Supplier<Double> restTimes) {
        return this;
    }
    

    @Override
    public String toString() {
        return String.format("self-check %s", super.toString());
    }
}
