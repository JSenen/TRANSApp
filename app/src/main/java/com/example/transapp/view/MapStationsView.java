package com.example.transapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.transapp.R;
import com.example.transapp.domain.DataSingleton;
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
        DataSingleton dataSingleton = DataSingleton.getInstance();
        long idStation = dataSingleton.getIdStation();
        longitude = dataSingleton.getLongitude();
        latitude = dataSingleton.getLatitude();
        stationName = dataSingleton.getStationName();

        Log.i("TAG","LATITUDE ---"+latitude);
        Log.i("TAG","LONGITUDE ---"+longitude);


        //Metodos para colocar el marcador en el mapa segun los campos
        initializePointManager();
        setCameraPosition(latitude,longitude);
        addMarker(latitude,longitude,stationName);

    }

    private void initializePointManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, annotationConfig);
    }
    private void setCameraPosition(double latitude, double longitude) {
        CameraOptions cameraPosition = new CameraOptions.Builder()
                .center(Point.fromLngLat(longitude, latitude))
                .pitch(40.0)
                .zoom(10.5)
                .bearing(0.0) //Orientacion Norte
                .build();
        mapView.getMapboxMap().setCamera(cameraPosition);
    }
    private void addMarker(double latitude, double longitude, String stationName) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(Point.fromLngLat(longitude, latitude))
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_station_gps_marker_foreground));


        pointAnnotationManager.create(pointAnnotationOptions);
    }
    /** Menu barra de tareas */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.taskbar_menu,menu);
        return true;
    }

    /** Al volver al Adpater anterior le volvemos a pasar idLinea para que adpater se actualice de nuevo */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Regresa a la pantalla anterior

        Intent intent = new Intent(this,SeeStationesActivityView.class);
        intent.putExtra("RESULT_DATA", idStation);
        setResult(RESULT_OK, intent);
        finish();

        return true;
    }



}