package com.jenshen.reflection.injection;


import com.jenshen.reflection.injection.superDagger.Inject;
import com.jenshen.reflection.injection.superDagger.InjectHelper;

public class AndroidDeveloper extends Developer{

    @Inject
    private Skill skillFirst;

    @Inject
    private Skill skillSecond;

    public AndroidDeveloper() {
        InjectHelper.setInjects(this);
    }

    @Override
    public String getSkills() {
        return skillFirst.toString() + ", " + skillSecond.toString();
    }
}
