package com.example.transapp.domain;

public class DataSingleton {
    /** Clase usada para pasar datos entre Activities*/
    private static DataSingleton instance = null;

    private long idStation;
    private double longitude;
    private double latitude;
    private String stationName;

    private DataSingleton() {}

    public static DataSingleton getInstance() {
        if (instance == null) {
            instance = new DataSingleton();
        }
        return instance;
    }

    public long getIdStation() {
        return idStation;
    }

    public void setIdStation(long idStation) {
        this.idStation = idStation;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
