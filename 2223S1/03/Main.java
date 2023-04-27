class Main{

    public static void Main(String args[]) {


    }

    public int getTotalMarks(List<Student> students) {
        return students.streams().map(x -> x.getMarks().mapToObj(x -> x.get().getMarks()).reduce(0, (a, b) -> a + b));
    }

}
