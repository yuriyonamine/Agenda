package com.example.play.yuri.agenda;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = (Student) studentList.getItemAtPosition(position);
                Intent goToFormIntent = new Intent(StudentListActivity.this, RegisterFormActivity.class);
                goToFormIntent.putExtra("student", student);
                startActivity(goToFormIntent);
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
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Student student = (Student) studentList.getItemAtPosition(info.position);

        MenuItem callMenuItem = menu.add("Call");
        callMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (ActivityCompat.checkSelfPermission(StudentListActivity.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(StudentListActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, 123);
                } else {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + student.getPhone()));
                    startActivity(callIntent);
                }

                return false;
            }
        });

        MenuItem smsMenuItem = menu.add("Sms");
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setData(Uri.parse("sms:" + student.getPhone()));
        smsMenuItem.setIntent(smsIntent);

        String site = student.getSite();

        if (site == null) {
            site = "";
        }

        if (!site.startsWith("http://")) {
            site = "http://" + site;
        }

        MenuItem goToWebsiteMenuItem = menu.add("Go to website");
        Intent websiteIntent = new Intent(Intent.ACTION_VIEW);

        websiteIntent.setData(Uri.parse(site));
        goToWebsiteMenuItem.setIntent(websiteIntent);

        MenuItem showAddressOnMapsMenuItem = menu.add("Show address on maps");
        Intent showAddressIntent = new Intent(Intent.ACTION_VIEW);
        showAddressIntent.setData(Uri.parse("geo:0,0?q=" + student.getAddress()));
        showAddressOnMapsMenuItem.setIntent(showAddressIntent);

        MenuItem deleteMenuItem = menu.add("Delete");
        deleteMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                StudentDAO studentDAO = new StudentDAO(StudentListActivity.this);
                studentDAO.delete(student);
                Toast.makeText(StudentListActivity.this, "Student " + student.getName() + " was deleted.", Toast.LENGTH_SHORT).show();
                loadList();
                return false;
            }
        });
    }
}
