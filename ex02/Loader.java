class Loader {
    private final int number;

    Loader(int number) {
        this.number = number;
    }

    int getIdentifier() {
        return this.number; 
    }

    @Override
    public String toString() {
        return String.format("Loader #%d", this.number);
    }
}
