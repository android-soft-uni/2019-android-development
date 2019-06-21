package com.inveitix.a09_architecture;

public class Cream extends Extras {

    public Cream(Bevarage bevarage) {
        super(bevarage);
    }

    @Override
    double internalCost() {
        return 0.5;
    }
}
