package com.jenshen.reflection.injection.superDagger;

import com.jenshen.reflection.injection.AndroidDeveloper;
import com.jenshen.reflection.injection.Skill;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * Created by user on 18.11.15.
 */

public class Module {

    private static final String skills[] = {"Java", "AndroidSDK", "XML", "RX java", "Dependency Injection", "Tests", "SQLite"};

    public static AndroidDeveloper getNewAndroidDeveloper(Class clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (AndroidDeveloper) clazz.newInstance();
    }

    public static Skill getNewSkill(Class clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor constructor = clazz.getConstructor(new Class[]{String.class});
        Random random = new Random();
        int i = random.nextInt(7);
        return (Skill) constructor.newInstance(new Object[]{skills[i]});
    }
}