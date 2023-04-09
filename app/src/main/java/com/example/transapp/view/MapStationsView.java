package com.example.transapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import com.example.transapp.R;
import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;

public class MapStationsView extends AppCompatActivity {

    private double longitude;
    private double latitude;
    private long idStation;
    private String stationName;
    private MapView mapView;
    private Point point;
    private PointAnnotationManager pointAnnotationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_stations_line);

        //Recuperamos los elementos del layout
        mapView = findViewById(R.id.mapViewStation);

        //Recogemos datos de la estacion
        Intent intent = getIntent();
        idStation = intent.getLongExtra("idLine",0);
        latitude = intent.getLongExtra("Latitude",0);
        longitude = intent.getLongExtra("Longitude",0);
        stationName = intent.getStringExtra("StationName");

        Log.d("TAG","LATITUDE ---------------------"+latitude);
        Log.d("TAG","LONGITUDE ---------------------"+longitude);

        if (idStation == 0){
            return;
        }

        //Metodos para colocar el marcador en el mapa segun los campos
        initializePointManager();
        setCameraPosition(latitude,longitude);
        addMarker(latitude,longitude);

    }

    private void initializePointManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, annotationConfig);
    }
    private void setCameraPosition(double latitude, double longitude) {
        CameraOptions cameraPosition = new CameraOptions.Builder()
                .center(Point.fromLngLat(longitude, latitude))
                .pitch(60.0)
                .zoom(10.5)
                .bearing(-17.6)
                .build();
        mapView.getMapboxMap().setCamera(cameraPosition);
    }
    private void addMarker(double latitude, double longitude) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(Point.fromLngLat(longitude, latitude))
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_station_gps_marker_foreground));

        pointAnnotationManager.create(pointAnnotationOptions);
    }



}