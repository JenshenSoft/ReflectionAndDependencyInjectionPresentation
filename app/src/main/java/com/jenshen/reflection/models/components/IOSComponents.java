package com.jenshen.reflection.models.components;

import com.jenshen.reflection.MainActivity;
import com.jenshen.reflection.models.module.IOSmodule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by user on 19.11.15.
 */
@Singleton
@Component(modules = {IOSmodule.class})
public interface IOSComponents {
    void inject(MainActivity activity);
}
