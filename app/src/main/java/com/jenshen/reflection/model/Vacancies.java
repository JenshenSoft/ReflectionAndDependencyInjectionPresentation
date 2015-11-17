package com.jenshen.reflection.model;

import java.io.Serializable;

public class Vacancies implements Serializable, Cloneable {

    private final int countOfVacancies;

    private long countOfAndroidDevelopers = 32l;

    private long countOfIOsDevelopers;

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

    protected long findAndroidDeveloper(String[] capabilities) {
        return countOfAndroidDevelopers;
    }
}