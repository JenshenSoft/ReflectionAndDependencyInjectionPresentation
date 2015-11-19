package com.jenshen.reflection.models.module;


import com.jenshen.reflection.models.IOSDeveloper;
import com.jenshen.reflection.models.ObjectiveC;

import javax.inject.Singleton;

import dagger.Provides;

public class IOSmodule {

    @Provides
    @Singleton
    IOSDeveloper getIOSDeveloper(){
        return new IOSDeveloper();
    }

    @Provides @Singleton
    ObjectiveC getObjectiveC(){
        return new ObjectiveC("OMG");
    }

}
