class SolidCuboid extends Cuboid implements Solid {  
    private final SolidImpl impl;

    SolidCuboid(double height, double width, double length, double density) {
        super(height, width, length);
        this.impl = new SolidImpl(this, density);
    }
    
    public double mass() {
        return this.impl.mass();
    }

    @Override
    public String toString() {
        return String.format("solid-%s with a mass of %.2f", 
                super.toString(), this.mass()); 
    }
}
