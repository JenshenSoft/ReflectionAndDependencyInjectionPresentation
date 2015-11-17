package com.jenshen.reflection;

import android.util.Log;

import com.jenshen.reflection.model.Jelvix;
import com.jenshen.reflection.model.Vacancies;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionFeature {

    public void getClassInfo(Vacancies vacancies) throws IllegalAccessException, InstantiationException {
        Object object;
        if (vacancies == null) {
            object = Vacancies.class.newInstance();
        } else {
            object = vacancies;
        }
        Class clazz = object.getClass();

        // выводим название пакета
        Package p = clazz.getPackage();
        showLog("package " + p.getName() + ";");

        // начинаем декларацию класса с модификаторов
        int modifiers = clazz.getModifiers();
        showLog(getModifiers(modifiers));
        // выводим название класса
        showLog("class " + clazz.getSimpleName() + " ");

        // выводим название родительского класса
        showLog("extends " + clazz.getSuperclass().getSimpleName() + " ");

        // выводим интерфейсы, которые реализует класс
        Class[] interfaces = clazz.getInterfaces();
        for (int i = 0, size = interfaces.length; i < size; i++) {
            showLog(i == 0 ? "implements " : "");
            showLog(interfaces[i].getSimpleName());
        }
        showLog("{");

        // выводим поля класса
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            showLog(getModifiers(field.getModifiers())
                    + getType(field.getType()) + " " + field.getName() + " = "  + ";");
        }

        // выводим констукторы класса
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor c : constructors) {
            showLog(getModifiers(c.getModifiers()) +
                    clazz.getSimpleName() + "(" + getParameters(c.getParameterTypes()) + ") { }");
        }

        // выводим методы класса
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            // получаем аннотации
            Annotation[] annotations = m.getAnnotations();
            for (Annotation a : annotations)
                showLog("@" + a.annotationType().getSimpleName() + " \n");

            showLog(getModifiers(m.getModifiers()) +
                    getType(m.getReturnType()) + " " + m.getName() + "(" + getParameters(m.getParameterTypes()) + ") { }");
        }

        showLog("}");
    }

    public String getModifiers(int m) {
        String modifiers = "";
        if (Modifier.isPublic(m)) modifiers += "public ";
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

    private void showLog(String message) {
        Log.i("Reflection test", message);
    }

    public void changeCountOfDevelopers(Vacancies vacancies, int count) {
        try {
            Class ftClass = vacancies.getClass();
            Field field = ftClass.getDeclaredField("countOfIOsDevelopers");
        //    field.set(vacancies, (Integer) field.get(vacancies) + count);

            field.setAccessible(true);

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

            field.set(null, 3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
