import java.util.List;

class Admin {

    Admin() {

    }

    void process(List<Student> students) {
        for (Student s : students) {
            System.out.println(s.getId() + " : " + s.getCAP());
        }
    }
}
