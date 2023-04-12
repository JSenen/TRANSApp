package com.example.transapp.view;

import static com.mapbox.core.constants.Constants.PRECISION_6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.transapp.R;
import com.example.transapp.domain.DataSingleton;
import com.mapbox.api.directions.v5.DirectionsCriteria;
import com.mapbox.api.directions.v5.MapboxDirections;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.api.directions.v5.models.RouteOptions;
import com.mapbox.core.constants.Constants;
import com.mapbox.geojson.LineString;
import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.extension.style.layers.LayerUtils;
import com.mapbox.maps.extension.style.layers.generated.LineLayer;
import com.mapbox.maps.extension.style.sources.SourceUtils;
import com.mapbox.maps.extension.style.sources.generated.GeoJsonSource;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.navigation.ui.maps.route.line.model.MapboxRouteLineOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapStationsView extends AppCompatActivity implements Callback<DirectionsResponse> {

    private double longitude;
    private double latitude;
    private long idStation;
    private String stationName;
    private MapView mapView;
    private Point point;
    private PointAnnotationManager pointAnnotationManager;
    private Point origin, destination;
    private String vistaLlama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_stations_line);

        //Recuperamos los elementos del layout
        mapView = findViewById(R.id.mapViewStation);

        //Recogemos la vista que llamo a esta
        vistaLlama = getIntent().getStringExtra("VistaLLama");

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

        //FIXME Para pruebas
        origin = Point.fromLngLat(2.1699,41.3879);
        destination = Point.fromLngLat(longitude,latitude);
        calculateRoute(origin,destination);
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
        //Identificamos la pantalla que llamo a esta para volver alli
        if (vistaLlama.equals("SeeStationsAdapter")){
            Intent intent = new Intent(this,SeeStationesActivityView.class);
            intent.putExtra("RESULT_DATA", idStation);
            setResult(RESULT_OK, intent);
            finish();
            return true;
        }else if (vistaLlama.equals("UserSeeStationsAdapter")){
            Intent intent = new Intent(this, UserSeeListStationsView.class);
            setResult(RESULT_OK, intent);
            finish();
        }

            return false;

    }
    /** Calculo de ruta entre 2 puntos */
    public void calculateRoute(Point origin, Point destination){

        RouteOptions routeOptions = RouteOptions.builder()
                .baseUrl(Constants.BASE_API_URL)
                .user(Constants.MAPBOX_USER)
                .profile(DirectionsCriteria.PROFILE_WALKING)
                .coordinatesList(List.of(origin, destination))
                .build();
        //llamada a la api de Mapbox
        MapboxDirections mapboxDirections = MapboxDirections.builder()
                .routeOptions(routeOptions)
                .accessToken(getString(R.string.mapbox_access_token))
                .build();
        mapboxDirections.enqueueCall(this);
    }

    /** Metodos implementados por llamada a la api mapbox */
    @Override
    public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
        //Obtenemos ruta validando que no sea nula
        if (response.isSuccessful() && response.body() != null && response.body().routes().size() > 0) {
            DirectionsRoute routePointToPoint = response.body().routes().get(0);
            // hacer algo con la ruta obtenida

            //Actualizamos mapa
            mapView.getMapboxMap().getStyle(new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@NonNull Style style) {
                    Log.d("TAG","Ruta calculada");
                    LineString routeLine = LineString.fromPolyline(routePointToPoint.geometry(), PRECISION_6);
                    //Creamos identificador al mapa para dibujar la ruta
                    GeoJsonSource routeSource = new GeoJsonSource.Builder("trace-source")
                            .geometry(routeLine)
                            .build();
                    //Creamos origen de la capa
                    LineLayer routeLayer = new LineLayer("trace-layer","trace-source")
                            .lineWidth(7.f)
                            .lineColor(Color.BLUE)
                            .lineOpacity(1f);

                    //Añadimos el origen
                    SourceUtils.addSource(style,routeSource);
                    //Añadimos la capa
                    LayerUtils.addLayer(style,routeLayer);


                }
            });
        } else {
            //TODO manejar el caso en que la respuesta es null o no tiene rutas
        }



    }

    @Override
    public void onFailure(Call<DirectionsResponse> call, Throwable t) {
        Log.d("TAG","No se ha podido calcualr la ruta ",t);

    }
}