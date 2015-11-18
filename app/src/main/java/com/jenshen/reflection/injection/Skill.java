package com.jenshen.reflection.injection;

public class Skill {

    public String description;

    public Skill (String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
