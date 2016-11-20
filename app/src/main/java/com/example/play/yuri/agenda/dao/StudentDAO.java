package com.example.play.yuri.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.play.yuri.agenda.model.Student;

public class StudentDAO extends SQLiteOpenHelper {

    public StudentDAO(Context context) {
        super(context, "Agenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE STUDENT (id INTEGER PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "ADDRESS TEXT, " +
                "phone TEXT, " +
                "site TEXT, " +
                "rating REAL);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS Students";
        db.execSQL(query);
        onCreate(db);
    }

    public void save(Student student) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues datas = new ContentValues();
        datas.put("name", student.getName());
        datas.put("address", student.getAddress());
        datas.put("phone", student.getPhone());
        datas.put("site", student.getSite());
        datas.put("rating", student.getRating());
        db.insert("Students", null, datas);
    }
}
