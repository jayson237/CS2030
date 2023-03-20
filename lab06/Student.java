class Student extends KeyableMap<Module> {
    
    Student(String name) {
        super(name);
    }

    Student(String name, ImmutableMap<String, Module> stu) {
        super(name, stu);
    }

    @Override
    Student put(Module mod) {
        return new Student(super.getKey(), super.getMap().put(mod.getKey(), mod));
    }
}
