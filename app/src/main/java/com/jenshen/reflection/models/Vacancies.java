package com.jenshen.reflection.models;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import java.io.Serializable;

public class Vacancies implements Serializable, Cloneable {

    private static int countOfIOsDevelopers = 2;
    private final int countOfVacancies;
    private long countOfAndroidDevelopers = 32l;

    public Vacancies(int countOfVacancies) {
        this.countOfVacancies = countOfVacancies;
    }

    public Vacancies() {
        countOfVacancies = 0;
    }

    @Deprecated
    protected static Integer findPascalDeveloper(String[] capabilities) {
        return 0;
    }

    protected long findAndroidDeveloper(@NonNull String[] capabilities) {
        return countOfAndroidDevelopers;
    }

    @CheckResult
    public int getCountOfVacancies() {
        return countOfVacancies;
    }
}