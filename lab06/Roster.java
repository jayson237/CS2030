class Roster extends KeyableMap<Student> {
    
    Roster(String year) {
        super(year);
    }

    Roster(String year, ImmutableMap<String, Student> map) {
        super(year, map);
    }

    String getGrade(String name, String module, String ass) {
        return super.getMap().get(name)
            .flatMap(x -> x.get(module))
            .flatMap(x -> x.get(ass))
            .map(x -> x.getGrade())
            .orElse(String.format("No such record: %s %s %s", name, module, ass));
    }

    Roster add(String name, String module, String ass, String grade) {
        Student stud = this.get(name).orElseGet(() -> { 
            return new Student(name); });
        Module mod = stud.get(module).orElseGet(() -> {
            return new Module(module); });
        Assessment assess = mod.get(ass).orElseGet(() -> {
            return new Assessment(ass, grade); });
        if (!(getGrade(name, module, ass).contains("No such record"))) {
            assess = new Assessment(ass, grade);
        }
        return this.put(stud.put(mod.put(assess))); 
    }

    @Override
    Roster put(Student student) {
        return new Roster(super.getKey(), super.getMap().put(student.getKey(), student));
    }
}
