package com.example.play.yuri.agenda;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.play.yuri.agenda.model.Student;

import java.util.List;

public class StudentListAdapter extends BaseAdapter {

    private Context context;
    private List<Student> studentList;

    public StudentListAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return studentList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Student student = studentList.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        View listView = convertView;
        if (convertView == null) {
            listView = inflater.inflate(R.layout.student_list_items, parent, false);
        }

        TextView nameItem = (TextView) listView.findViewById(R.id.name_item);
        nameItem.setText(student.getName());

        TextView phoneItem = (TextView) listView.findViewById(R.id.phone_item);
        phoneItem.setText(student.getPhone());

        TextView addressItem = (TextView) listView.findViewById(R.id.address_item);
        if (addressItem != null) {
            addressItem.setText(student.getAddress());
        }

        TextView siteItem = (TextView) listView.findViewById(R.id.site_item);
        if (siteItem != null) {
            siteItem.setText(student.getSite());
        }

        ImageView photoItem = (ImageView) listView.findViewById(R.id.photo_item);
        String photoPath = student.getPhotoPath();
        if (photoPath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(photoPath);
            Bitmap reducedBitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
            photoItem.setImageBitmap(reducedBitmap);
            photoItem.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        return listView;
    }
}
