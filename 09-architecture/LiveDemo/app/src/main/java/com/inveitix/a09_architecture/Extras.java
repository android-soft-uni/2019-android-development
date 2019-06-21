package com.inveitix.a09_architecture;

public abstract class Extras extends Bevarage {

    private Bevarage rootElement;

    public Extras(Bevarage bevarage) {
        rootElement = bevarage;
    }

    abstract double internalCost();

    @Override
    double cost() {
        return internalCost() + rootElement.cost();
    }
}
