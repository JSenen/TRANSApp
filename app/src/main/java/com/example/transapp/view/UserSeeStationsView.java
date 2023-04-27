package com.example.transapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.transapp.R;
import com.example.transapp.contract.UserSeeStationsContract;
import com.example.transapp.domain.Stations;
import com.example.transapp.presenter.UserSeeStationsPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.MapboxMap;
import com.mapbox.maps.extension.style.layers.properties.generated.TextAnchor;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;

import java.util.ArrayList;
import java.util.List;


public class UserSeeStationsView extends AppCompatActivity implements UserSeeStationsContract.View {

    private MapView mapView;
    private MapboxMap mapboxMap;
    private PointAnnotationManager pointAnnotationManager;
    private UserSeeStationsPresenter presenter;
    private long idLine;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_see_stations_view);

        //Recuperamos la id de la linea. Asignamos 0 por defecto si no hay id
        Intent intent = getIntent();
        idLine = intent.getLongExtra("idLine",0);

        if (idLine == 0){
            return;
        }

        mapView = findViewById(R.id.mapUserStationsView);
        initializePointManager();

        presenter = new UserSeeStationsPresenter(this,idLine,this);
        presenter.userLoadAllStations();

        //Boton flotante listener
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserSeeStationsView.this,UserSeeListStationsView.class);
                intent.putExtra("idLine",idLine);
                startActivity(intent);

            }
        });



    }
    private void initializePointManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, annotationConfig);
    }
    private void addMarker(Point point, String title, long idStation) {

        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(point)
                .withTextField(title)
                .withTextSize(22.5f)
                .withIconHaloColor(Color.WHITE) //Borde
                .withTextHaloWidth(5.5f) //ancho
                .withTextColor(Color.RED)
                .withTextHaloColor("white")
                .withTextAnchor(TextAnchor.TOP)
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_station_gps_marker_foreground));
        pointAnnotationManager.create(pointAnnotationOptions);
    }

    private void setCameraPosition(Point point) {
        CameraOptions cameraPosition = new CameraOptions.Builder()
                .center(point)
                .pitch(60.0)
                .zoom(5.5)
                .bearing(-7.6)
                .build();
        mapView.getMapboxMap().setCamera(cameraPosition);
    }
    private void addStationToMap(List<Stations> stations) {
        for (Stations station : stations) {
            Point point = Point.fromLngLat(station.getLongitude(), station.getLatitude());
            addMarker(point, station.getName(), station.getId());
        }

    }


    @Override
    public void showAllStations(List<Stations> stationsList) {
        centerMapOnPoints(stationsList);
        addStationToMap(stationsList);

    }

    /** Menu barra de tareas */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.taskbar_admin_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Regresa a la pantalla anterior
        Intent intent = new Intent(this, SeeLinesActivityView.class);
        startActivity(intent);
        return true;
    }
    /** Metodo para centrar camara en media de los puntos */
    private void centerMapOnPoints(List<Stations> stationsList) {
        if (stationsList.isEmpty()) {
            return;
        }

        // Calcula el centroide de los puntos
        double latSum = 0, lngSum = 0;
        for (Stations point : stationsList) {
            latSum += point.getLatitude();
            lngSum += point.getLongitude();
        }
        double latAvg = latSum / stationsList.size();
        double lngAvg = lngSum / stationsList.size();

        // Establece el centro del mapa en el centroide de los puntos
        Point point = Point.fromLngLat(lngAvg,latAvg);
        setCameraPosition(point);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}