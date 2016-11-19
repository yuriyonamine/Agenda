package com.example.play.yuri.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class StudentListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        String[] students= {"Daniel", "John", "Valdir", "Heitor"};
        ListView studentList = (ListView) findViewById(R.id.student_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, students);
        studentList.setAdapter(adapter);

        Button newStudent = (Button) findViewById(R.id.new_student);
        newStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToRegisterFormIntent = new Intent(StudentListActivity.this, RegisterFormActivity.class);
                startActivity(goToRegisterFormIntent);
            }
        });
    }
}
