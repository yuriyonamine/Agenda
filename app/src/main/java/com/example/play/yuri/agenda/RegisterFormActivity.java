package com.example.play.yuri.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.play.yuri.agenda.dao.StudentDAO;
import com.example.play.yuri.agenda.model.Student;

public class RegisterFormActivity extends AppCompatActivity {
    private RegisterStudentFormHelper registerStudentFormHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);
        registerStudentFormHelper = new RegisterStudentFormHelper(this);

        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("student");
        if (student != null) {
            registerStudentFormHelper.fillForm(student);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.form_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.form_menu_ok:
                StudentDAO dao = new StudentDAO(this);
                Student student = registerStudentFormHelper.getStudent();

                if (student.getId() != null) {
                    dao.update(student);
                } else {
                    dao.save(student);
                }
                dao.close();

                Toast.makeText(RegisterFormActivity.this, "Student " + student.getName() + " registered.", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
