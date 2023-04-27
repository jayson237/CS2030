class PA {
    private final List<Level> levels;

    Stream<Integer> getMarks() {
        return levels.stream().mapToInt(x -> x.getMarks());
    }

}
