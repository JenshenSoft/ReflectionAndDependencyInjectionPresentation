package com.jenshen.reflection;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jenshen.reflection.injection.AndroidDeveloper;
import com.jenshen.reflection.injection.JavaDeveloper;
import com.jenshen.reflection.injection.JavaScriptDeveloper;
import com.jenshen.reflection.injection.Skill;
import com.jenshen.reflection.injection.superDagger.Module;
import com.jenshen.reflection.model.Vacancies;
import com.jenshen.reflection.tests.SpeedReflectionTest;

import rx.Observable;
import rx.Subscription;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final SpeedReflectionTest speedReflectionTest = new SpeedReflectionTest();
    private Subscription subscribe;
    private EditText countOfVacancies_editText;
    private Button setVacancies_button;
    private Button getInfo_button;
    private Button createAD_button;
    private TextView info_textView;
    private ReflectionFeature reflectionFeature;
    private Vacancies vacancies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        reflectionFeature = new ReflectionFeature();
        vacancies = new Vacancies(7);
        countOfVacancies_editText = (EditText) findViewById(R.id.countOfVacancies_editText);
        setVacancies_button = (Button) findViewById(R.id.setVacancies_button);
        createAD_button = (Button) findViewById(R.id.createAD_button);
        getInfo_button = (Button) findViewById(R.id.getInfo_button);
        info_textView = (TextView) findViewById(R.id.info_textView);

        setVacancies_button.setOnClickListener(v1 -> {
            try {
                String count = countOfVacancies_editText.getText().toString();
                reflectionFeature.changeCountOfDevelopers(vacancies, Integer.valueOf(count));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        getInfo_button.setOnClickListener(v1 -> {
            try {
                info_textView.setText(reflectionFeature.getClassInfo(vacancies));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        createAD_button.setOnClickListener(v1 -> {
            try {
                createDevelopers();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
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
        createDevelopers();
    }

    @Override
    protected void onDestroy() {
        if (subscribe != null && !subscribe.isUnsubscribed())
            subscribe.unsubscribe();
        super.onDestroy();
    }


    /* di */

    private void createDevelopers() {
        try {
            Skill firstSkill = Module.getNewSkill(Skill.class);
            Skill secondSkill = Module.getNewSkill(Skill.class);

            //Setter Injection
            JavaScriptDeveloper javaScriptDeveloper = new JavaScriptDeveloper();
            javaScriptDeveloper.setSkillFirst(firstSkill);
            javaScriptDeveloper.setSkillSecond(secondSkill);
            showMessage(javaScriptDeveloper.getSkills(), JavaScriptDeveloper.class);

            //Constructor Injection
            JavaDeveloper javaDeveloper = new JavaDeveloper(firstSkill, secondSkill);
            showMessage(javaDeveloper.getSkills(), JavaDeveloper.class);

            //Runtime Injection
            AndroidDeveloper androidDeveloper = new AndroidDeveloper();
            showMessage(androidDeveloper.getSkills(), AndroidDeveloper.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showMessage(String text, Class clazz) {
        Toast.makeText(this, clazz.getSimpleName() + ": " + text, Toast.LENGTH_SHORT).show();
    }
}
