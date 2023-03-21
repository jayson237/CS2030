class Module extends KeyableMap<Assessment> {
    
    Module(String code) {
        super(code);
    } 
    
    Module(String code, ImmutableMap<String, Assessment> assessments) {
        super(code, assessments);
    }

    @Override
    Module put(Assessment ass) {
        return new Module(super.getKey(), super.getMap().put(ass.getKey(), ass));
    }
}
