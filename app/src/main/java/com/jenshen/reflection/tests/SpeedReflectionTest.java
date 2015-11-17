package com.jenshen.reflection.tests;

import android.util.Log;

import com.jenshen.reflection.model.Jelvix;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SpeedReflectionTest {
    public static final int LOOPS_IN_STEP_COUNT = 10_000_000;

    public void addDeveloper_changeField() {
        Jelvix jelvix = new Jelvix();
        jelvix.reset();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < LOOPS_IN_STEP_COUNT; i++) {
            jelvix.developers += i;
        }
        long endTime = System.currentTimeMillis();
        showTimeInLog("add developer without reflection: ", startTime, endTime, jelvix);
    }

    public void addDeveloperWithReflection_changeField() {
        Jelvix jelvix = new Jelvix();
        jelvix.reset();
        try {
            Class ftClass = jelvix.getClass();
            Field field = ftClass.getField("developers");
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < LOOPS_IN_STEP_COUNT; i++) {
                field.set(jelvix, (Integer) field.get(jelvix) + i);
            }
            long endTime = System.currentTimeMillis();
            showTimeInLog("add developer with reflection (change field): ", startTime, endTime, jelvix);
        } catch (NoSuchFieldException | IllegalAccessException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void addDeveloperWithReflection_changeFieldAccessible() {
        Jelvix jelvix = new Jelvix();
        jelvix.reset();
        try {
            Class ftClass = jelvix.getClass();
            Field field = ftClass.getField("developers");
            field.setAccessible(true);
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < LOOPS_IN_STEP_COUNT; i++) {
                field.set(jelvix, (Integer) field.get(jelvix) + i);
            }
            long endTime = System.currentTimeMillis();
            showTimeInLog("add developer with reflection (change field): ", startTime, endTime, jelvix);
        } catch (NoSuchFieldException | IllegalAccessException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void addDeveloper_callMethod() {
        Jelvix jelvix = new Jelvix();
        jelvix.reset();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < LOOPS_IN_STEP_COUNT; i++) {
            jelvix.addDevelopers(i);
        }
        long endTime = System.currentTimeMillis();
        showTimeInLog("add developer without reflection (call method): ", startTime, endTime, jelvix);
    }

    public void addDeveloperWithReflection_callMethod() {
        Jelvix jelvix = new Jelvix();
        jelvix.reset();
        try {
            Class ftClass = jelvix.getClass();
            Method method = ftClass.getDeclaredMethod("addDevelopers", int.class);
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < LOOPS_IN_STEP_COUNT; i++) {
                method.invoke(jelvix, i);
            }
            long endTime = System.currentTimeMillis();
            showTimeInLog("add developer with reflection (call method): ", startTime, endTime, jelvix);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void addDeveloperWithReflection_callMethodWithAccessible() {
        Jelvix jelvix = new Jelvix();
        jelvix.reset();
        try {
            Class ftClass = jelvix.getClass();
            Method method = ftClass.getDeclaredMethod("addDevelopers", int.class);
            method.setAccessible(true);
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < LOOPS_IN_STEP_COUNT; i++) {
                method.invoke(jelvix, i);
            }
            long endTime = System.currentTimeMillis();
            showTimeInLog("add developer with reflection (call method With Accessible): ", startTime, endTime, jelvix);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    /*

    public void addDeveloperWithOutReflection_decorator() {
        Jelvix jelvix = new Jelvix();
        jelvix.reset();
        IJelvix jelvixDecorator = new JelvixDecorator(jelvix);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < LOOPS_IN_STEP_COUNT; i++) {
            jelvixDecorator.addDevelopers(i);
        }
        long endTime = System.currentTimeMillis();
        showTimeInLog("add developer without reflection (decorator): ", startTime, endTime, jelvix);
    }

    public void addDeveloperWithReflection_decorator() {
        Jelvix jelvix = new Jelvix();
        jelvix.reset();
        IJelvix jelvixDecorator = new JelvixDecorator(jelvix);
        try {
            Method method = JelvixDecorator.class.getDeclaredMethod("addDevelopers", int.class);
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < LOOPS_IN_STEP_COUNT; i++) {
                method.invoke(jelvixDecorator, i + 1);
            }
            long endTime = System.currentTimeMillis();
            showTimeInLog("add developer with reflection (decorator): ", startTime, endTime, jelvix);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | NullPointerException e) {
            e.printStackTrace();
        }
    }*/

    private void showTimeInLog(String tag, long startTime, long endTime, Jelvix jelvix) {
        Log.i("Reflection test", tag + String.valueOf(endTime - startTime));
        // Log.i("Reflection test", tag + "after " + String.valueOf(jelvix.getCountOfDevelopers()));
        Log.i("Reflection test", "-------");
    }

}
