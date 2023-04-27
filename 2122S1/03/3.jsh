// a
int[] convert(int[] arr, IntUnaryOperator fn) {
    return Arrays.stream(arr)
                 .map(fn)
                 .toArray();
}

// another way
int[] convert(int[] arr, Function<Integer, Integer> f) {
    return Arrays.stream(arr).map(x -> f.apply(x)).toArray();
}

// b
int[] convert(int[] arr, Function<int[], int[]> f) {
    return f.apply(arr);
}

//c
Function<int[], int[]> f = arr -> IntStream.range(0, arr.length).filter(x -> x % 2 == 0).map(y
 -> arr[y] * 2).toArray()




