package com.example.play.yuri.agenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.play.yuri.agenda.dao.StudentDAO;
import com.example.play.yuri.agenda.model.Student;

public class RegisterFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);
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
                StudentDAO studentDAO = new StudentDAO(this);
                RegisterStudentFormHelper registerStudentFormHelper = new RegisterStudentFormHelper(this);
                Student student = registerStudentFormHelper.getStudent();

                studentDAO.save(student);
                studentDAO.close();

                Toast.makeText(RegisterFormActivity.this, "Student " + student.getName() + " registered.", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
