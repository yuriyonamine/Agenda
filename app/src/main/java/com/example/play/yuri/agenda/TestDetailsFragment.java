package com.example.play.yuri.agenda;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.play.yuri.agenda.model.Test;

public class TestDetailsFragment extends Fragment {
    private TextView subjectField;
    private TextView dateField;
    private ListView topicsList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_details_fragment, container, false);

        subjectField = (TextView) view.findViewById(R.id.test_details_subject);
        dateField = (TextView) view.findViewById(R.id.test_details_date);
        topicsList = (ListView) view.findViewById(R.id.test_details_topics);

        Bundle parameters = getArguments();
        if (parameters != null) {
            Test prova = (Test) parameters.getSerializable("test");
            fillFields(prova);
        }

        return view;
    }

    public void fillFields(Test test) {
        subjectField.setText(test.getSubject());
        dateField.setText(test.getDate());

        ArrayAdapter<String> adapterTopicos =
                new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, test.getTopics());
        topicsList.setAdapter(adapterTopicos);
    }
}

