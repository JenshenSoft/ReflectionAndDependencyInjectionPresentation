package com.jenshen.reflection.models;

public class JelvixDecorator implements IJelvix {

    private IJelvix jelvix;

    public JelvixDecorator(IJelvix jelvix) {
        this.jelvix = jelvix;
    }

    public void addDevelopers(int developers) {
        jelvix.addDevelopers(developers);
    }

    @Override
    public int getCountOfDevelopers() {
        return jelvix.getCountOfDevelopers();
    }

    @Override
    public void reset() {
        jelvix.reset();
    }
}
