void toggling(TrafficLight t, int n) {
    System.out.print(t);
    for (int i = 0; i < n; i++) {
        t = t.toggle();
        System.out.print(  " + t);
    }
    System.out.println();
}

// b
toggling(new RedLight("red"), 5)

// d input for 2c 
RedLight redLight = new RedLight("red") {
    RedLight red = this;
    public TrafficLight toggle() {                                                                            
    return new AmberLight("amber") {
            TrafficLight toggle() {
                return new GreenLight("green") {
                    TrafficLight toggle() {
                        return new AmberLight("amber") {
                            TrafficLight toggle() {
                                return red;
                            }
                        };
                    }
                };
            }
        };
    }
};
