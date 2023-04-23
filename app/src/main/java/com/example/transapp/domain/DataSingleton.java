package com.example.transapp.domain;

public class DataSingleton {
    /** Clase usada para pasar datos entre Activities*/
    private static DataSingleton instance = null;

    private long idStation;
    private double longitude;
    private double latitude;
    private String stationName;
    private String hopen;
    private String hclose;
    private boolean ptoInfo;
    private boolean wifi;
    private boolean busStation;
    private boolean taxiStation;

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

    public String getHopen() {
        return hopen;
    }

    public void setHopen(String hopen) {
        this.hopen = hopen;
    }

    public String getHclose() {
        return hclose;
    }

    public void setHclose(String hclose) {
        this.hclose = hclose;
    }

    public boolean isPtoInfo() {
        return ptoInfo;
    }

    public void setPtoInfo(boolean ptoInfo) {
        this.ptoInfo = ptoInfo;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isBusStation() {
        return busStation;
    }

    public void setBusStation(boolean busStation) {
        this.busStation = busStation;
    }

    public boolean isTaxiStation() {
        return taxiStation;
    }

    public void setTaxiStation(boolean taxiStation) {
        this.taxiStation = taxiStation;
    }
}
