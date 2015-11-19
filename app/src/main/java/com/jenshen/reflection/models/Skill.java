package com.jenshen.reflection.models;

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
