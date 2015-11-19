package com.jenshen.reflection.models;



public class Jelvix implements IJelvix {
    public int developers =  0;

    public void addDevelopers(int developers) {
        this.developers += developers;
    }

    @Override
    public int getCountOfDevelopers() {
        return developers;
    }

    @Override
    public void reset() {
        developers = 0;
    }

    public void addDevelopers(Developers developers) {
        this.developers += developers.newDevelopers;
    }
}
