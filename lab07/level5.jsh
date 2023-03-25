Log<Integer> sum (int n) {
    Log<Integer> sum = Log.<Integer>of(0, "hit base case!");
    for (int i = 1; i <= n; i++) {
        int add = i;
        sum = sum.flatMap(x-> Log.<Integer>of(x + add, String.format("adding %d", x + 1)));
    }
    return sum;
}
