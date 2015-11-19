package com.jenshen.reflection.models;


import javax.inject.Inject;

public class IOSDeveloper extends Developer {

    @Inject
    private ObjectiveC objectiveC;

    public IOSDeveloper() {

    }

    @Override
    public String getSkills() {
        return objectiveC.description;
    }
}
