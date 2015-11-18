package com.jenshen.reflection.injection.superDagger;


import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import java.lang.reflect.Field;
import java.util.List;

public class Parser {

    public static List<Field> getFieldWithInjectAnnotation(Class clazz) throws Exception {
        Field[] fields = clazz.getDeclaredFields();
        return Stream.of(fields)
                .filter(field -> field.isAnnotationPresent(Inject.class))
                .collect(Collectors.toList());
    }
}
