package com.example.play.yuri.agenda;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.play.yuri.agenda.dao.StudentDAO;
import com.example.play.yuri.agenda.model.Student;

import java.io.File;

public class RegisterFormActivity extends AppCompatActivity {
    private static final int IMAGE_CAPTURE_CODE = 200;
    private RegisterStudentFormHelper registerStudentFormHelper;
    private String photoPath;

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

        View cameraButton = findViewById(R.id.form_camera_button);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                photoPath = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File photoFile = new File(photoPath);
                Uri uriPhoto = FileProvider.getUriForFile(RegisterFormActivity.this, "com.examle.play.yuri.fileprovider", photoFile);

                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriPhoto);

                startActivityForResult(openCameraIntent, IMAGE_CAPTURE_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_CAPTURE_CODE) {
                registerStudentFormHelper.loadPhoto(photoPath);
            }
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
