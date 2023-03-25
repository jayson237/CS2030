Log<Integer> sum (int n) {
    Log<Integer> total = Log.<Integer>of(0, "hit base case!");
    for (int i = 1; i < n + 1; i++) {
        int add = i;
        total = total.flatMap(x-> Log.<Integer>of(x + add, "adding " + add));
    }
    return total;
}

Log<Integer> f(int n) {
    Log<Integer> collatz = Log.<Integer>of(n);
    while (n != 1) {
        if (n % 2 == 0) {
            collatz = collatz.flatMap(x -> Log.<Integer>of(x / 2, x + " / " + 2));
            n /= 2;
        } else {
            collatz = collatz.flatMap(x -> Log.<Integer>of
                    (3 * x + 1, 3 + "(" + x  + ")" + " + " + 1));
            n  = 3 * n + 1;
        }
    }
    return collatz.flatMap(x -> Log.<Integer>of(1, "1"));
}
