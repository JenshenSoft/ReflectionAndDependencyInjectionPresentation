package com.jenshen.reflection.injection;


public class JavaDeveloper extends Developer {

    private Skill skillFirst;

    private Skill skillSecond;

    public JavaDeveloper(Skill skillFirst, Skill skillSecond) {
        this.skillFirst = skillFirst;
        this.skillSecond = skillSecond;
    }

    @Override
    public String getSkills() {
        return skillFirst.toString() + ", " + skillSecond.toString();
    }
}
