class Roster extends KeyableMap<Student> {
    
    Roster(String year) {
        super(year);
    }

    Roster(String year, ImmutableMap<String, Student> map) {
        super(year, map);
    }

    @Override
    Roster put(Student student) {
        return new Roster(super.getKey(), super.getMap().put(student.getKey(), student));
    }

    String getGrade(String id, String code, String name) {
        return super.getMap().get(id)
            .flatMap(x -> x.get(code))
            .flatMap(x -> x.get(name))
            .map(x -> x.getGrade())
            .orElse(String.format("No such record: %s %s %s", id, code, name));
    }

    Roster add(String id, String code, String name, String grade) {
        Student stud = this.get(id).orElseGet(() -> { 
            return new Student(id); });
        Module mod = stud.get(code).orElseGet(() -> {
            return new Module(code); });
        Assessment ass = mod.get(name).orElseGet(() -> {
            return new Assessment(name, grade); });
        if (!(getGrade(id, code, name).contains("No such record"))) {
            ass = new Assessment(name, grade);
        }
        return this.put(stud.put(mod.put(ass))); 
    }    
}
