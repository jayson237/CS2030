import java.util.List;

class Bursar {

    Bursar() {

    }

    void process(List<Student> students) {
        for (Student s : students) {
            System.out.println(s.getId() + " : " + s.getLoan());
        }
    }
}
