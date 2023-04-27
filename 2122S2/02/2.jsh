// a
double pow(double a, long b) {
    return Stream.<Double>generate(() -> a)
        .limit(b)
        .reduce(1.0, (x, y) -> x * y);
}

double seriesPi(long n) {
    return Stream.<Integer>iterate(1, x -> x + 1)
        .limit(n)
        .mapToDouble(i -> pow(-1, i + 1) * 4.0 / (2 * i - 1))           .sum();
}

// b
double approxPi(long n) {
    return (Stream.<Point>generate(() -> new Point(getRand(), getRand()))
        .limit(n)
        .filter(x -> unitCircle.contains(x)).count() / (double) n) * 4.0;
}


