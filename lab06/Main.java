import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Roster roster = new Roster("Roster");

        while (n > 0) {
            String name = sc.next();
            String module = sc.next();
            String ass = sc.next();
            String grade = sc.next();
            roster = roster.add(name, module, ass, grade); 
            n--;
        }

        boolean ask = true;
        while (ask) {
            if (!sc.hasNext()) {
                break;
            }
            String name = sc.next();
            String module = sc.next();
            String ass = sc.next();
            if (name.contains("^D") || module.contains("^D") || ass.contains("^D")) {
                break;
            }
            System.out.println(roster.getGrade(name, module, ass));
        }
    }
}    
