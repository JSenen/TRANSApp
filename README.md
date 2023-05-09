# TRANSapp
## Actividad Aprendizaje 2EVA - 2ºDAM - PROGRAMACION MULTIMEDIA DISPOSITIVOS MOVILES

![Java](https://img.shields.io/badge/Java-red?style=for-the-badge&logo=Java&logoColor=white)
![Android](https://img.shields.io/badge/androidstudio-green?style=for-the-badge&logo=androidstudio&logoColor=white)

***

Aplicación creada con el propósito de trabajar conjuntamente con la API CityTravel.
En la aplicación se puede consumir una serie de datos sobre lineas de transporte. 
En la misma se hace uso de la API CityTravel desarrolada en la 1ª evaluación y disponible
en el siguiente enlace (https://github.com/JSenen/CityTravel)
Pudiendose comprobar su documentación en el siguiente enlace (https://jsenen.github.io/CityTravel/#/)

En esta aplicación para sistema Android, con sdk 33 y mínimo 29 se,se ha hecho uso de varias implementaciones,
 siendo las principales las siguientes. **Retrofit2** para la comunicación asíncrona con la API, 
y de la base de datos **Room** de la biblioteca de persistencia de Android, con la ayuda de la dependencia de **Loombok**
En el aspecto visual, se ha utilizado la librería **Material Design**, y para los mapas se ha usado
**MapBox**

```
    //MaterialDesign
    implementation 'com.google.android.material:material:1.8.0'
    implementation 'com.google.android.gms:play-services-location:21.0.1'

    //CardView
    implementation 'androidx.cardview:cardview:1.0.0'

    //Loombok
    implementation "org.projectlombok:lombok:1.18.26"
    annotationProcessor "org.projectlombok:lombok:1.18.26"

    //Retrofit2
    implementation "com.squareup.retrofit2:retrofit:2.9.0"
    implementation "com.squareup.retrofit2:converter-gson:2.9.0"

    //GSON
    implementation 'com.google.code.gson:gson:2.10.1'

    //Room
    implementation "androidx.room:room-runtime:2.5.1"
    annotationProcessor "androidx.room:room-compiler:2.5.1"

    //MapBox
    implementation 'com.mapbox.maps:android:10.12.1'
    implementation 'com.mapbox.mapboxsdk:mapbox-sdk-turf:6.11.0'
    implementation 'com.mapbox.navigation:core:2.11.0'
    implementation 'com.mapbox.navigation:android:2.11.0'
    implementation 'com.mapbox.navigation:ui-dropin:2.11.0'

```
## USO DE LA APP:

El uso de la aplicación se divide en **2 accesos distintos**.
Un **acceso libre** para **usuarios** y otro **acceso restringido** para **administradores**, al ser necesario para
este último, encontrarse previamente registrado en la API y poder obtener un **token** **JWT**


Las **funciones de la aplicación** son las siguientes, distinguiendo por accesos:

![img.png](https://github.com/JSenen/TRANSApp/blob/master/DiagramaesquemaAPP.png)

**OPCIONES USUARIO**

1. **Opción Lineas:** El usuario accede a un listado completo de la lineas de transporte disponibles
   1. **Detalle Linea:** Se visualiza un mapa centrado en las estaciones que componen la linea. Pulsando sobre el botón flotante 
      del mapa se visualiza el listado de estaciones de la linea pudiendo acceder a los detalles de cada una, y guardandola en 
      favoritos en caso de querer disponer de ellos sin conexión a la api
   2. **Detalles Estación:** Visualiza los detalles de la estación seleccionada
   3. **Mapa Estación:** Geolocaliza sobre un mapa la posición de la estación seleccionada y muestra la ruta a pie, con indicación de distancia, desde
      la posición del usuario
2. **Opción favoritos:** Usuario accede a los archivos que ha seleccionado para guardar en favoritos y poder visualizarlos
   aunque no disponga de aaceso a la API.

**OPCIONES ADMINISTRADOR**

1. **Login:** Es necesario encontrarse registrado en la API para poder recuperar un token __Json Web Token__ e identificarse como administrador
2. **Lineas:**  Todas las opciones sobre las lineas de trasnporte. **Crear, Eliminar, Editar y Visualizar**
3. **Estaciones:**  Todas las opciones sobre las lineas de trasnporte. **Crear, Eliminar, Editar y Visualizar**

**Códigos petición API**
```

    @GET("lines")
    Call<List<Line>> getAllLines();
    @GET("line/{lineId}/stations")
    Call<List<Stations>> getStationsByLine(@Path("lineId") long id);
    @GET("line/{lineId}/stations")
    Call<List<Stations>> getStationsByParams(@Path("lineId") long id, @Query("wifi") boolean wifi,
                                           @Query("busStation") boolean busStation,
                                           @Query("taxiStation") boolean taxiStation);

    @POST("token")
    Call<Token> getToken(@Body UserLogin userLogin);

    @POST("station/{id}/station")
    Call<Stations> addStationToLine(@Header("Authorization") String token, @Path("id") long id, @Body Stations stations);

    @POST("line")
    Call<Line> addOneLine(@Header("Authorization") String token, @Body Line line);

    @PUT("stations/{id}")
    Call<Stations> updateStation(@Header("Authorization") String token, @Path("id") long id, @Body Stations stations);

    @PUT("lines/{id}")
    Call<Line> updateLine(@Header("Authorization") String token, @Path("id") long id, @Body Line linebody);

    @DELETE("stations/{id}")
    Call<Void> deleteStationByid(@Header("Authorization") String token, @Path("id") long id);
    @DELETE("lines/{id}")
    Call<Void> deleteLineById(@Header("Authorization") String token, @Path("id") long id);
```

**LOGIN JWT**

El logín con la api en la que se recibe un token *_Json Web Token_*, para su almacenamiento en el dispositivo
se ha utilizado las *_Shared Preferences_*; y poder enviarlo en las peticiones a la api de la zona administrador 
en las que es necesario.

```
 @Override
    public void login(String username, String passwrd) {
        model.login(username, passwrd, new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful()){
                    String token =response.body().getToken();
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("token", token);
                    editor.apply();
                    view.showSnackbar(context.getString(R.string.Login_ok));
                }else{
                    view.showSnackbar(context.getString(R.string.Login_ko));
                    view.goMainActivity();
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                view.showSnackbar(context.getString(R.string.Login_ko));
            }
        });
```

Tambíen se ha usado la base de datos persistente *_Room_* del dispositivo, para que el usuario pueda
guardar como favoritos la información que necesite recuperar sin necesidad de disponer de conexión a 
la Api.

```
@Override
    public void loadAllFavorites(OnLoadFavoritesListener listener, Context context) {
        List<FavoriteStations> favoriteStationsList = new ArrayList<>();
        //Añadir a la Base de Datos
        final FavStationsDB database = Room.databaseBuilder(context, FavStationsDB.class,DATA_BASE_NAME)
                .allowMainThreadQueries().build();
        favoriteStationsList =database.favoriteStationsDAO().getAll();
        listener.onFavOk(favoriteStationsList);

        Log.i("TAG","Dato almacenado "+favoriteStations.getName());

    }
```

***

**MAPBOX**
En esta aplicación se ha hecho uso de MapBox, tanto para la visualización de mapas con la posición actual
del dispositivo y de los puntos de interes, así como la funcionalidad de disponer de la ruta desde
la posición al punto interesado.

```
@Override
    public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
        //Obtenemos ruta validando que no sea nula
        if (response.isSuccessful() && response.body() != null && response.body().routes().size() > 0) {
            DirectionsRoute routePointToPoint = response.body().routes().get(0);
            // Ruta no nula hacer algo con la ruta obtenida

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

                    double distanceInMeters = routePointToPoint.distance();
                    distanceString = String.format("%.2f km", distanceInMeters / 1000.0);
                    TextView banner = mapView.findViewById(R.id.txt_distance);
                    banner.setText(distanceString);


                }
            });
        } else {
            //TODO manejar el caso en que la respuesta es null o no tiene rutas
        }



    }
```


