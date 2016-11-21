package com.example.play.yuri.agenda;

import android.widget.EditText;
import android.widget.RatingBar;

import com.example.play.yuri.agenda.model.Student;

/**
 * Created by Play on 19/11/2016.
 */
public class RegisterStudentFormHelper {

    private final RegisterFormActivity registerFormActivity;
    private Student student;
    private EditText nameField;
    private EditText addressField;
    private EditText phoneField;
    private EditText siteField;
    private RatingBar ratingField;

    public RegisterStudentFormHelper(RegisterFormActivity registerFormActivity) {
        this.registerFormActivity = registerFormActivity;
        this.student = new Student();
        this.nameField = (EditText) this.registerFormActivity.findViewById(R.id.register_form_name);
        this.addressField = (EditText) this.registerFormActivity.findViewById(R.id.register_form_address);
        this.phoneField = (EditText) this.registerFormActivity.findViewById(R.id.register_form_phone);
        this.siteField = (EditText) this.registerFormActivity.findViewById(R.id.register_form_site);
        this.ratingField = (RatingBar) this.registerFormActivity.findViewById(R.id.register_form_rating);

    }

    public Student getStudent() {
        this.student.setName(nameField.getText().toString());
        this.student.setAddress(addressField.getText().toString());
        this.student.setPhone(addressField.getText().toString());
        this.student.setAddress(addressField.getText().toString());
        this.student.setRating(Double.valueOf(ratingField.getProgress()));

        return this.student;
    }

    public void fillForm(Student student) {
        this.student = student;
        this.nameField.setText(student.getName());
        this.addressField.setText(student.getAddress());
        this.phoneField.setText(student.getPhone());
        this.siteField.setText(student.getSite());
        this.ratingField.setProgress(student.getRating().intValue());

    }

}
