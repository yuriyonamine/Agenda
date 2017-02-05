package com.example.play.yuri.agenda.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.play.yuri.agenda.R;
import com.example.play.yuri.agenda.converter.StudentConverter;
import com.example.play.yuri.agenda.dao.StudentDAO;
import com.example.play.yuri.agenda.model.Student;

import java.util.List;

public class SendDataTask extends AsyncTask<Void, Void, String> {
    private Context context;
    private ProgressDialog dialog;

    public SendDataTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        this.dialog = ProgressDialog.show(context, context.getString(R.string.loading_title_dialog), context.getString(R.string.loading_message_dialog), true, false, null);
        this.dialog.show();
    }

    @Override
    protected String doInBackground(Void... params) {
        StudentDAO studentDAO = new StudentDAO(context);
        List<Student> students = studentDAO.findAll();
        studentDAO.close();

        StudentConverter converter = new StudentConverter();
        String studentsJson = converter.toJson(students);

        WebClient webClient = new WebClient();
        String response = webClient.post(studentsJson);

        return response;
    }

    @Override
    protected void onPostExecute(String response) {
        this.dialog.dismiss();
        Toast.makeText(context, response, Toast.LENGTH_LONG).show();
    }
}
