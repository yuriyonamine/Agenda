package com.example.play.yuri.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.play.yuri.agenda.dao.StudentDAO;
import com.example.play.yuri.agenda.model.Student;

import java.util.List;

public class StudentListActivity extends AppCompatActivity {
    private ListView studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        studentList = (ListView) findViewById(R.id.student_list);

        Button newStudent = (Button) findViewById(R.id.new_student);
        newStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToRegisterFormIntent = new Intent(StudentListActivity.this, RegisterFormActivity.class);
                startActivity(goToRegisterFormIntent);
            }
        });

        registerForContextMenu(studentList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadList();
    }

    private void loadList() {
        StudentDAO studentDAO = new StudentDAO(this);
        List<Student> students = studentDAO.findAll();

        ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, students);
        studentList.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deleteMenuItem = menu.add("Delete");
        deleteMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo  info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Student student = (Student) studentList.getItemAtPosition(info.position);
                StudentDAO studentDAO = new StudentDAO(StudentListActivity.this);
                studentDAO.delete(student);
                Toast.makeText(StudentListActivity.this, "Student "+student.getName()+" was deleted.", Toast.LENGTH_SHORT).show();
                loadList();
                return false;
            }
        });
    }
}
