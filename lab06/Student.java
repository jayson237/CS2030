class Student extends KeyableMap<Module> {
    
    Student(String name) {
        super(name);
    }

    Student(String name, ImmutableMap<String, Module> student) {
        super(name, student);
    }

    @Override
    Student put(Module mod) {
        return new Student(super.getKey(), super.getMap().put(mod.getKey(), mod));
    }
}
