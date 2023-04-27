class Student {
    private final List<Optional<PA>> listPA;
    
    Stream<Optional<Integer>> getMarks() {
        return listPA.stream()
            .filter(x -> x.isPresent()).map(y -> y.getMarks().reduce(0, (a, b) -> a + b));
    }
}

