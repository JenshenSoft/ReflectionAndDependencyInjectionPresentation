package com.jenshen.reflection.injection;


public class JavaScriptDeveloper extends Developer {

    private Skill skillFirst;

    private Skill skillSecond;

    public JavaScriptDeveloper() {
    }

    public void setSkillFirst(Skill skillFirst) {
        this.skillFirst = skillFirst;
    }

    public void setSkillSecond(Skill skillSecond) {
        this.skillSecond = skillSecond;
    }

    @Override
    public String getSkills() {
        return skillFirst.toString() + ", " + skillSecond.toString();
    }
}
