package com.example.play.yuri.agenda;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.play.yuri.agenda.dao.StudentDAO;
import com.example.play.yuri.agenda.model.Student;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback {
    private GoogleMap googleMap;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        LatLng latLng = getCoordinates("Rua Deputado Lacerda Franco, 86");
        if (latLng != null) {
            centerIn(latLng);
        }
        StudentDAO studentDAO = new StudentDAO(getContext());
        for (Student student : studentDAO.findAll()) {
            LatLng coordinates = getCoordinates(student.getAddress());
            if (coordinates != null) {
                MarkerOptions marker = new MarkerOptions();
                marker.position(coordinates);
                marker.title(student.getName());
                marker.snippet(String.valueOf(student.getRating()));
                googleMap.addMarker(marker);
            }
        }
        studentDAO.close();
    }

    private LatLng getCoordinates(String address) {
        try {
            Geocoder geocoder = new Geocoder(getContext());
            List<Address> locals = geocoder.getFromLocationName(address, 1);
            if (!locals.isEmpty()) {
                return new LatLng(locals.get(0).getLatitude(), locals.get(0).getLongitude());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void centerIn(LatLng latLng){
        CameraUpdate camera = CameraUpdateFactory.newLatLng(latLng);
        googleMap.moveCamera(camera);
    }
}