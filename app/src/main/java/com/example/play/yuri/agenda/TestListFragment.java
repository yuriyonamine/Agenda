package com.example.play.yuri.agenda;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.play.yuri.agenda.R;
import com.example.play.yuri.agenda.model.Test;

import java.util.Arrays;
import java.util.List;

public class TestListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tests_list_fragment, container, false);
        List<String> portugueseTopics = Arrays.asList("Subject", "Predicate", "Direct Object", "Indirect Object");
        Test portugueseTest = new Test("Portuguese", "06/02/2017", portugueseTopics);

        List<String> mathTopics = Arrays.asList("Trigonometry", "Natural Numbers");
        Test mathTest = new Test("Mathematic", "06/05/2017", mathTopics);

        List<Test> tests = Arrays.asList(portugueseTest, mathTest);

        ArrayAdapter<Test> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, tests);

        ListView listView = (ListView) view.findViewById(R.id.tests_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Test test = (Test) parent.getItemAtPosition(position);
                Toast.makeText(view.getContext(), test.getSubject(), Toast.LENGTH_LONG).show();

                TestsActivity testsActivity = (TestsActivity) getActivity();
                testsActivity.selectTest(test);
            }
        });


        return view;
    }
}
