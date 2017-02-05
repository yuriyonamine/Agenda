package com.example.play.yuri.agenda.converter;

import android.widget.ListView;

import com.example.play.yuri.agenda.model.Student;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

/**
 * Created by Play on 04/02/2017.
 */
public class StudentConverter {

    public String toJson(List<Student> students) {
        JSONStringer jsonStringer = new JSONStringer();
        String json = "";
        try {
            jsonStringer = jsonStringer.object()
                    .key("list")
                    .array()
                    .object()
                    .key("aluno")
                    .array();

            for (Student student : students) {
                jsonStringer.object()
                        .key("id").value(student.getId())
                        .key("nome").value(student.getName())
                        .key("endereco").value(student.getAddress())
                        .key("telefone").value(student.getPhone())
                        .key("site").value(student.getSite())
                        .key("nota").value(student.getRating())
                        .endObject();
            }

            jsonStringer
                    .endArray()
                    .endObject()
                    .endArray()
                    .endObject();

            json = jsonStringer.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }
}
