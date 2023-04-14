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


