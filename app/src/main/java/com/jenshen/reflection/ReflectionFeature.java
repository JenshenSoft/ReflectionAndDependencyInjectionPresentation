package com.jenshen.reflection;

import com.jenshen.reflection.model.Vacancies;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionFeature {

    public String getClassInfo(Vacancies vacancies) throws IllegalAccessException, InstantiationException {
        StringBuilder stringBuilder = new StringBuilder();
        Object object;
        if (vacancies == null) {
            object = Vacancies.class.newInstance();
        } else {
            object = vacancies;
        }
        Class clazz = object.getClass();

        // выводим название пакета
        Package p = clazz.getPackage();
        stringBuilder.append("package ").append(p.getName()).append(";").append("\n");

        // начинаем декларацию класса с модификаторов
        int modifiers = clazz.getModifiers();
        stringBuilder.append(getModifiers(modifiers));
        // выводим название класса
        stringBuilder.append("class ").append(clazz.getSimpleName()).append(" ").append("\n");

        // выводим название родительского класса
        stringBuilder.append("extends ").append(clazz.getSuperclass().getSimpleName()).append(" ").append("\n");

        // выводим интерфейсы, которые реализует класс
        Class[] interfaces = clazz.getInterfaces();
        for (int i = 0, size = interfaces.length; i < size; i++) {
            stringBuilder.append(i == 0 ? "implements " : "");
            stringBuilder.append(interfaces[i].getSimpleName());
        }
        stringBuilder.append("\n").append("{").append("\n");

        // выводим поля класса
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            stringBuilder.append(getModifiers(field.getModifiers()))
                    .append(getType(field.getType()))
                    .append(" ")
                    .append(field.getName())
                    .append(" = ")
                    .append(field.get(vacancies))
                    .append(";")
                    .append("\n");
        }
        stringBuilder.append("\n");
        // выводим констукторы класса
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor c : constructors) {
            stringBuilder.append(getModifiers(c.getModifiers()))
                    .append(clazz.getSimpleName())
                    .append("(")
                    .append(getParameters(c.getParameterTypes()))
                    .append(") { }")
                    .append("\n");
        }
        // выводим методы класса
        stringBuilder.append(getMethods(clazz.getDeclaredMethods()));

        stringBuilder.append("\n").append("}");
        return stringBuilder.toString();
    }

   public String getMethods(Method[] methods) {
       StringBuilder stringBuilder = new StringBuilder();
       for (Method m : methods) {
           // получаем аннотации
           Annotation[] annotations = m.getDeclaredAnnotations();
           for (Annotation a : annotations)
               stringBuilder.append("@").append(a.annotationType().getSimpleName()).append(" \n");

           stringBuilder.append(getModifiers(m.getModifiers()))
                   .append(getType(m.getReturnType()))
                   .append(" ")
                   .append(m.getName())
                   .append("(")
                   .append(getParameters(m.getParameterTypes()))
                   .append(") { }");
       }
       return stringBuilder.toString();
    }

    public String getModifiers(int m) {
        String modifiers = "";
        if (Modifier.isPublic(m)) modifiers += "public ";
        if (Modifier.isFinal(m)) modifiers += "final ";
        if (Modifier.isStatic(m)) modifiers += "static ";
        if (Modifier.isProtected(m)) modifiers += "protected ";
        if (Modifier.isPrivate(m)) modifiers += "private ";
        if (Modifier.isStatic(m)) modifiers += "static ";
        if (Modifier.isAbstract(m)) modifiers += "abstract ";
        return modifiers;
    }

    public String getType(Class clazz) {
        String type = clazz.isArray()
                ? clazz.getComponentType().getSimpleName()
                : clazz.getSimpleName();
        if (clazz.isArray()) type += "[]";
        return type;
    }

    public String getParameters(Class[] params) {
        String p = "";
        for (int i = 0, size = params.length; i < size; i++) {
            if (i > 0) p += ", ";
            p += getType(params[i]) + " param" + i;
        }
        return p;
    }

    public void changeCountOfDevelopers(Vacancies vacancies, int count) throws NoSuchFieldException, IllegalAccessException {
        Class ftClass = vacancies.getClass();
        Field f1 = getField(ftClass, "countOfVacancies");
        f1.set(vacancies, count);
    }

    public Field getField(Class clazz, String name) throws NoSuchFieldException {
        Field f1 = clazz.getDeclaredField(name);
        f1.setAccessible(true);
        return f1;
    }
}
