# TRANSapp
## Actividad Aprendizaje 2EVA - 2ºDAM - PROGRAMACION MULTIMEDIA DISPOSITIVOS MOVILES

![Java](https://img.shields.io/badge/Java-red?style=for-the-badge&logo=Java&logoColor=white)
![Android](https://img.shields.io/badge/androidstudio-green?style=for-the-badge&logo=androidstudio&logoColor=white)

***

Aplicación creada con el propósito de consumir los datos de la API CityTravel.
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



