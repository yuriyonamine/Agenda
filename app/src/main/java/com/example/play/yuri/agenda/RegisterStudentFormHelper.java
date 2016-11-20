package com.example.play.yuri.agenda;

import android.widget.EditText;
import android.widget.RatingBar;

import com.example.play.yuri.agenda.model.Student;

/**
 * Created by Play on 19/11/2016.
 */
public class RegisterStudentFormHelper {

    private final RegisterFormActivity registerFormActivity;
    private EditText name;
    private EditText address;
    private EditText phone;
    private EditText site;
    private RatingBar rating;

    public RegisterStudentFormHelper(RegisterFormActivity registerFormActivity) {
        this.registerFormActivity = registerFormActivity;
    }

    public Student getStudent() {
        Student student = new Student();
        name = (EditText) this.registerFormActivity.findViewById(R.id.register_form_name);
        address = (EditText) this.registerFormActivity.findViewById(R.id.register_form_address);
        phone = (EditText) this.registerFormActivity.findViewById(R.id.register_form_phone);
        site = (EditText) this.registerFormActivity.findViewById(R.id.register_form_site);
        rating = (RatingBar) this.registerFormActivity.findViewById(R.id.register_form_rating);
        student.setName(name.getText().toString());
        student.setAddress(address.getText().toString());
        student.setPhone(address.getText().toString());
        student.setAddress(address.getText().toString());
        student.setRating(Double.valueOf(rating.getProgress()));

        return student;
    }
}
