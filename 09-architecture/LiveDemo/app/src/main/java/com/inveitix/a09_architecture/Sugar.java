package com.inveitix.a09_architecture;

public class Sugar extends Extras {

    public Sugar(Bevarage bevarage) {
        super(bevarage);
    }

    @Override
    double internalCost() {
        return 0.1;
    }
}
