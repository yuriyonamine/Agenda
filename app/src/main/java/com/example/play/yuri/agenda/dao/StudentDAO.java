package com.example.play.yuri.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.play.yuri.agenda.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO extends SQLiteOpenHelper {

    public StudentDAO(Context context) {
        super(context, "Agenda", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE Students (id INTEGER PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "address TEXT, " +
                "phone TEXT, " +
                "site TEXT, " +
                "rating REAL, " +
                "photo_path TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void save(Student student) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues datas = getStudentContentValues(student);
        db.insert("Students", null, datas);
    }

    @NonNull
    private ContentValues getStudentContentValues(Student student) {
        ContentValues datas = new ContentValues();
        datas.put("name", student.getName());
        datas.put("address", student.getAddress());
        datas.put("phone", student.getPhone());
        datas.put("site", student.getSite());
        datas.put("rating", student.getRating());
        datas.put("photo_path", student.getPhotoPath());

        return datas;
    }

    public List<Student> findAll() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM Students;";
        Cursor cursor = db.rawQuery(query, null);
        List<Student> students = new ArrayList<Student>();
        while (cursor.moveToNext()) {
            Student student = new Student();
            student.setId(cursor.getInt(cursor.getColumnIndex("id")));
            student.setName(cursor.getString(cursor.getColumnIndex("name")));
            student.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            student.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
            student.setSite(cursor.getString(cursor.getColumnIndex("site")));
            student.setPhotoPath(cursor.getString(cursor.getColumnIndex("photo_path")));
            student.setRating(cursor.getDouble(cursor.getColumnIndex("rating")));

            students.add(student);
        }

        cursor.close();

        return students;
    }

    public void delete(Student student) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {student.getId().toString()};
        db.delete("Students", "id = ?", params);
    }

    public void update(Student student) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = getStudentContentValues(student);
        String[] params = {student.getId().toString()};
        db.update("Students", contentValues, "id = ?", params);
    }

    public boolean isStudent(String phone) {
        String sql = "select * from Students where phone = ?";
        Cursor cursor = getReadableDatabase().rawQuery(sql, new String[]{phone});
        int quantity = cursor.getColumnCount();
        cursor.close();

        return quantity > 0;
    }
}
