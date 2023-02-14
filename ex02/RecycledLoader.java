class RecycledLoader extends Loader {
    private static final int extraTime = 60;

    RecycledLoader(int number) {
        super(number);
    }

    @Override
    public String toString() {
        return String.format("Recycled %s serving %s", super.toString(), this.cruise.toString()); 
    }

}
