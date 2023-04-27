ImList<Integer> reverse(List<Integer> list) {
    return new ImList<Integer>(IntStream.range(0, list.size()).mapToObj(x -> list.get(list.size() - x - 1)).toList());
}

<E> List<Pair<E,E>> pairing(List<E> list) {
    return IntStream.range(0, list.size() - 1)
        .mapToObj(x -> x)
        .filter(index -> index % 2 == 0)
        .map(y -> new Pair<E, E>(list.get(y), list.get(y + 1)))
        .toList();
}

<E> List<E> merging(List<Pair<E,E>> list) {
    return list.stream()
        .flatMap(pair -> Stream.of(pair.first(), pair.second()))
        .toList();
}

