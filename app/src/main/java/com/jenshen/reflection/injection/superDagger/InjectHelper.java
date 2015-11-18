package com.jenshen.reflection.injection.superDagger;

import com.annimon.stream.Stream;
import com.jenshen.reflection.injection.Skill;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class InjectHelper {

    public static void setInjects(Object object) {
        try {
            Class clazz = object.getClass();
            List<Field> fieldWithInjectAnnotation = Parser.getFieldWithInjectAnnotation(clazz);
            if (!fieldWithInjectAnnotation.isEmpty()) {
                Stream.of(fieldWithInjectAnnotation).forEach(field -> {
                    field.setAccessible(true);
                    try {
                        setFieldsWithInject(field, object);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setFieldsWithInject(Field field, Object object) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        field.set(object, Module.getNewSkill(Skill.class));
    }
}
