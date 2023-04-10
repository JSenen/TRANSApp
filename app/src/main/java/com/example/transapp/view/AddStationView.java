package com.example.transapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.icu.util.Calendar;
import android.location.Location;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.transapp.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.maps.plugin.gestures.GesturesPlugin;
import com.mapbox.maps.plugin.gestures.GesturesUtils;


public class AddStationView extends AppCompatActivity implements Style.OnStyleLoaded  {

    private double longitude;
    private double latitude;
    private long idStation;
    private String stationName;
    private MapView mapView;
    private Point point;
    private PointAnnotationManager pointAnnotationManager;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_station_view);
        context = this;


        //Recuperamos los elementos del layout
        mapView = findViewById(R.id.mapViewAddStation);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);

        EditText editTextHoraOpen = findViewById(R.id.edtxt_addstation_hopen);
        EditText editTextHoraClose = findViewById(R.id.edtxt_addstation_hclose);

        //Creamos la instancia al cliente proveedor de posicion en la Activity
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        //Metodo para controlar el mapa y añadir marcador al pulsar sobre una posicion
        GesturesPlugin gesturesPlugin = GesturesUtils.getGestures(mapView);
        gesturesPlugin.addOnMapClickListener(point -> {
            delAllMakers();
            this.point = point;
            addMarketPoint(point);
            return true;
        });


        //Comprobamos permisos
        checkLocationPermission();
        initializePointManager();

        // Crear una instancia de Calendar y obtener la hora actual
        Calendar calendar = Calendar.getInstance();
        int hora = calendar.get(Calendar.HOUR_OF_DAY);
        int minuto = calendar.get(Calendar.MINUTE);

        // Crear un objeto TimePickerDialog con el estilo Material
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, com.google.android.material.R.style.MaterialAlertDialog_MaterialComponents_Title_Icon_CenterStacked, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // Aquí puedes hacer lo que necesites con la hora seleccionada
                editTextHoraOpen.setText(hourOfDay + ":" + minute);
                editTextHoraClose.setText(hourOfDay + ":" + minute);
            }
        }, hora, minuto, false);

        // Mostrar el diálogo de selección de tiempo al hacer clic en el EditText
        editTextHoraOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextHoraOpen.requestFocus();
                editTextHoraClose.requestFocus();
                timePickerDialog.show();
            }
        });

    }
    @Override
    public void onStyleLoaded(@NonNull Style style) {
        gps();
    }
    @SuppressLint("MissingPermission")
    private void gps() {
        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            longitude = location.getLongitude();
                            latitude = location.getLatitude();

                            //Colocamos camara en ultima posicion GPS
                            setCameraPosition(latitude, longitude);
                            //Colocamos indicador en la posición del GPS
                            addMarketGPS(latitude, longitude);
                        }
                    }
                });

    }
    private void initializePointManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, annotationConfig);
        Log.i("GPS AddPArk","Initialize Point Annotation");

    }
    private void addMarketGPS(double latitude, double longitude) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(Point.fromLngLat(longitude,latitude))
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_station_gps_marker_foreground));

        pointAnnotationManager.create(pointAnnotationOptions);
    }
    //Metodo establece configutaciones de visualizacion de la camara
    private void setCameraPosition(double latitude, double longitude){
        CameraOptions cameraPosition = new CameraOptions.Builder()
                .center(Point.fromLngLat(longitude,latitude))
                .pitch(45.0)
                .zoom(9.1)
                .bearing(-17.6)
                .build();
        mapView.getMapboxMap().setCamera(cameraPosition);

    }
    private void checkLocationPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
            }
        }

    }
    //Metodo para añadir marcador
    private void addMarketPoint(Point point) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(point)
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_station_gps_marker_foreground));
        pointAnnotationManager.create(pointAnnotationOptions);


    }
    //Borrar todos los marcadores
    private void delAllMakers() {
        pointAnnotationManager.deleteAll();
    }


    /** Menu barra de tareas Login */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.taskbar_admin_addstation,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.taskbar_admin_addstation_back){
            finish();//Cerramos Activity actual
            //Regresa a la pantalla anterior
            Intent intent = new Intent(this, LogedModLinesActivityView.class);
            startActivity(intent);
            return true;
        }else if(item.getItemId() == R.id.taskbar_admin_addstation_save){
            //Guardar la estación introducida
            Intent intent = new Intent(this, AddStationView.class);
            startActivity(intent);
            return true;
        }

        return false;
    }

}