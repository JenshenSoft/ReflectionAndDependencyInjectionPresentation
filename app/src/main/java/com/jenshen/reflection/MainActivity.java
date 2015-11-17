package com.jenshen.reflection;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.jenshen.reflection.model.Vacancies;
import com.jenshen.reflection.tests.SpeedReflectionTest;

import rx.Observable;
import rx.Subscription;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final SpeedReflectionTest speedReflectionTest = new SpeedReflectionTest();
    private Subscription subscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ReflectionFeature reflectionFeature = new ReflectionFeature();
        Vacancies vacancies = new Vacancies(7);
        try {
            reflectionFeature.getClassInfo(vacancies);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        reflectionFeature.changeCountOfDevelopers(vacancies, 33);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Start Tests", Snackbar.LENGTH_LONG)
                .setAction("GO!", v -> {
                    subscribe = Observable.just(null)
                            .subscribeOn(Schedulers.computation())
                            .observeOn(Schedulers.computation())
                            .subscribe(o -> {
                                        Log.d("Reflection test", "tests were started");
                                        speedReflectionTest.addDeveloper_changeField();
                                        speedReflectionTest.addDeveloperWithReflection_changeField();
                                        speedReflectionTest.addDeveloperWithReflection_changeFieldAccessible();
                                        speedReflectionTest.addDeveloper_callMethod();
                                        speedReflectionTest.addDeveloperWithReflection_callMethod();
                                        speedReflectionTest.addDeveloperWithReflection_callMethodWithAccessible();
                                        //speedReflectionTest.addDeveloperWithOutReflection_decorator();
                                        //speedReflectionTest.addDeveloperWithReflection_decorator();
                                    }
                            );
                }).show());

    }

    @Override
    protected void onDestroy() {
        subscribe.unsubscribe();
        super.onDestroy();
    }
}
