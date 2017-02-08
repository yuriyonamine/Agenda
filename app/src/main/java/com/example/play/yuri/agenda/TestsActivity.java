package com.example.play.yuri.agenda;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.play.yuri.agenda.model.Test;

public class TestsActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tests_activity);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction tx = fragmentManager.beginTransaction();
        tx.replace(R.id.main_frame, new TestListFragment());
        if (isLandscapeMode()) {
            tx.replace(R.id.secondary_frame, new TestDetailsFragment());
        }

        tx.commit();
    }

    private boolean isLandscapeMode() {
        return getResources().getBoolean(R.bool.landscapeMode);
    }

    public void selectTest(Test test) {
        FragmentManager manager = getSupportFragmentManager();
        if (isLandscapeMode()) {
            TestDetailsFragment detalhesFragment =
                    (TestDetailsFragment) manager.findFragmentById(R.id.secondary_frame);
            detalhesFragment.fillFields(test);
        } else {
            FragmentTransaction tx = manager.beginTransaction();
            TestDetailsFragment detalhesFragment = new TestDetailsFragment();
            Bundle parametros = new Bundle();
            parametros.putSerializable("test", test);
            detalhesFragment.setArguments(parametros);
            tx.replace(R.id.main_frame, detalhesFragment);
            tx.addToBackStack(null);
            tx.commit();
        }
    }
}
