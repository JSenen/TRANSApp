package com.example.transapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.transapp.R;
import com.example.transapp.contract.EditStationContract;
import com.example.transapp.domain.DataSingleton;
import com.example.transapp.domain.Stations;
import com.example.transapp.presenter.EditStationPresenter;
import com.google.android.material.snackbar.Snackbar;
import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.maps.plugin.gestures.GesturesPlugin;
import com.mapbox.maps.plugin.gestures.GesturesUtils;

public class EditStationView extends AppCompatActivity implements EditStationContract.View {

    private EditStationPresenter presenter;
    private MapView mapView;
    private Point point;
    private PointAnnotationManager pointAnnotationManager;
    private String station,hopen,hclose;
    private boolean bus,taxi,wifi,ptoInfo;
    private double longitude,latitude;
    private long idStation;
    private Button buttonEdit;
    private Stations stationBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_station_view);

        //Recogemos datos de la estacion
        DataSingleton dataSingleton = DataSingleton.getInstance();
        idStation = dataSingleton.getIdStation();
        longitude = dataSingleton.getLongitude();
        latitude = dataSingleton.getLatitude();
        station = dataSingleton.getStationName();
        hopen = dataSingleton.getHopen();
        hclose = dataSingleton.getHclose();
        bus = dataSingleton.isBusStation();
        taxi = dataSingleton.isTaxiStation();
        wifi = dataSingleton.isWifi();
        ptoInfo = dataSingleton.isPtoInfo();

        //Recuperamos los elementos del layout
        mapView = findViewById(R.id.mapViewEditStation);
        buttonEdit = findViewById(R.id.button_modstation);

        showStationData();

        //Metodo para controlar el mapa y añadir marcador al pulsar sobre una posicion
        GesturesPlugin gesturesPlugin = GesturesUtils.getGestures(mapView);
        gesturesPlugin.addOnMapClickListener(point -> {
            delAllMakers();
            this.point = point;
            addMarketPoint(point);
            return true;
        });

        //Inicializar presenter y pasar Token para realizar endpoint
        SharedPreferences preferences = getSharedPreferences("MyPref",MODE_PRIVATE);
        String token = preferences.getString("token","");
        presenter = new EditStationPresenter(this,token,idStation,stationBody);

        //Listener boton modificar
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Recuperamos el body para enviar
                stationBody = createStationBody();
                presenter.editStation(token,idStation,stationBody);
            }
        });


    }
    private void showStationData() {

        //Recuperamos elementos y mostramos datos

        EditText stationName = findViewById(R.id.edtxt_modstation_name);
        EditText stationHopen = findViewById(R.id.edtxt_modstation_hopen);
        EditText stationHclos = findViewById(R.id.edtxt_modstation_hclose);
        CheckBox stationWifi = findViewById(R.id.checkbox_modstation_wifi);
        CheckBox stationBus = findViewById(R.id.checkbox_modstation_bus);
        CheckBox stattionTaxi = findViewById(R.id.checkbox_modstation_taxi);
        CheckBox stationInfo = findViewById(R.id.checkbox_modstation_info);

        stationName.setText(station);
        stationHopen.setText(hopen);
        stationHclos.setText(hclose);
        stationWifi.setChecked(wifi);
        stattionTaxi.setChecked(taxi);
        stationBus.setChecked(bus);
        stationInfo.setChecked(ptoInfo);


        //Metodos para colocar el marcador en el mapa segun los campos
        initializePointManager();
        setCameraPosition(latitude,longitude);
        addMarker(latitude,longitude);
    }

    /** Metodo para crear el body json necesario en la api con las modificaciones */
    private Stations createStationBody() {

        stationBody = new Stations();

        stationBody.setLongitude((float) longitude);
        stationBody.setLatitude((float) latitude);
        stationBody.setName(((EditText) findViewById(R.id.edtxt_modstation_name)).getText().toString());
        stationBody.setHopen(((EditText) findViewById(R.id.edtxt_modstation_hopen)).getText().toString());
        stationBody.setHclose(((EditText) findViewById(R.id.edtxt_modstation_hclose)).getText().toString());
        stationBody.setWifi(((CheckBox) findViewById(R.id.checkbox_modstation_wifi)).isChecked());
        stationBody.setBusStation(((CheckBox) findViewById(R.id.checkbox_modstation_bus)).isChecked());
        stationBody.setTaxiStation(((CheckBox) findViewById(R.id.checkbox_modstation_taxi)).isChecked());
        stationBody.setPtoInfo(((CheckBox) findViewById(R.id.checkbox_modstation_info)).isChecked());
        return stationBody;
    }

    /** Metodos MapBox */
    private void initializePointManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, annotationConfig);
    }
    private void addMarker(double latitude, double longitude) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(Point.fromLngLat(longitude, latitude))
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_station_gps_marker_foreground));


        pointAnnotationManager.create(pointAnnotationOptions);
    }

    private void setCameraPosition(double latitude, double longitude) {
        CameraOptions cameraPosition = new CameraOptions.Builder()
                .center(Point.fromLngLat(longitude, latitude))
                .pitch(40.0)
                .zoom(9.5)
                .bearing(0.0) //Orientacion Norte
                .build();
        mapView.getMapboxMap().setCamera(cameraPosition);
    }

    //Borrar todos los marcadores
    private void delAllMakers() {
        pointAnnotationManager.deleteAll();
    }
    private void addMarketPoint(Point point) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(point)
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_station_gps_marker_foreground));
        pointAnnotationManager.create(pointAnnotationOptions);
        //Rcuperamos datos nueva posicion
        latitude = point.latitude();
        longitude = point.longitude();

    }

    /** Menu barra de tareas */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.taskbarmenu_admin_stations,menu);
        return true;
    }

    /** Al volver al Adpater anterior le volvemos a pasar idLinea para que adpater se actualice de nuevo */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //Regresa a la pantalla anterior y modificar Recycler

        Intent intent = new Intent(this,SeeStationesActivityView.class);
        intent.putExtra("RESULT_DATA", idStation);
        setResult(RESULT_OK, intent);
        finish();

        return true;


    }

    /** Metodo respuesta del contract*/
    @Override
    public void showSnackBar(String message){
        Snackbar.make(findViewById(R.id.checkbox_modstation_info),message,Snackbar.LENGTH_LONG).show();
    }
}