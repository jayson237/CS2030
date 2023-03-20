import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Roster roster = new Roster("test");

        while (n > 0) {
            String id = sc.next();
            String code = sc.next();
            String name = sc.next();
            String grade = sc.next();
            roster = roster.add(id, code, name, grade); 
            n--;
        }

        boolean ask = true;
        while (sc.hasNext()) {
            String id = sc.next();
            String code = sc.next();
            String name = sc.next();
            if (id.contains("^D") || code.contains("^D") || name.contains("^D")) {
                break;
            }
            System.out.println(roster.getGrade(id, code, name));
        }
    }
}    
