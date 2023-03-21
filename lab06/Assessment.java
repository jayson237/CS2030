class Assessment implements Keyable {
    private final String key;
    private final String grade;

    Assessment(String key, String grade) {
        this.key = key;
        this.grade = grade;
    }

    String getGrade() {
        return this.grade;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String toString() {
        return String.format("{%s: %s}", this.key, this.grade);
    }
}
