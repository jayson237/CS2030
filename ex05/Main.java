import java.lang.Math;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.function.UnaryOperator;

class Main {

    static boolean isPrime(int n) {
        int sqrt = (int)Math.sqrt(n);
        return n > 1 && IntStream.rangeClosed(2,sqrt).noneMatch(x -> n % x == 0);
    }

    static IntStream twinPrimes(int n) {
        return IntStream.rangeClosed(2, n)
            .filter(x -> (isPrime(x) && isPrime(x + 2)) ||
                    (isPrime(x) && isPrime(x - 2)));
    }

    static String reverse(String str) {
        return Stream.<String>of(str.split("")).reduce("", (x, y) -> y + x);    
    } 

    static long countRepeats(List<Integer> list) {
        return IntStream.range(1, list.size())
            .filter(x -> x - 2 < 0 || list.get(x - 2) != list.get(x))
            .filter(x -> list.get(x - 1) == list.get(x))
            .count();
    }

    static UnaryOperator<List<Integer>> generateRule() {
        UnaryOperator<List<Integer>> rule = list -> IntStream.rangeClosed(0, list.size() - 1)
            .mapToObj(index -> list.get(index) == 1 ? 0 
                    : index == 0 ? (list.get(index + 1) == 1 ? 1 : 0)
                    : index == list.size() - 1 ? (list.get(index - 1) == 1 ? 1 : 0)
                    : (list.get(index - 1) == 1 || list.get(index + 1) == 1) ? 
                    ((list.get(index - 1) == 1 && list.get(index + 1) == 1) ? 0 : 1) : 0).toList();
        return rule;
    }

    static Stream<String> gameOfLife(List<Integer> list, UnaryOperator<List<Integer>> rule, int n) {
        return Stream.iterate(list, rule).limit(n).map(x -> {
            return IntStream.range(0, x.size()).mapToObj(y -> {
                if (x.get(y) == 0) {
                    return ".";
                }
                return "x";
            }).reduce("", (a, b) -> a + b);
        });
    }
}

