 abstract class Student {
    private final String id;

    Student(String id) {
        this.id = id;
    }

    double getCAP() {
        return 5.0;
    }

    String getId() {
        return this.id;
    }

    abstract double getLoan();
}
