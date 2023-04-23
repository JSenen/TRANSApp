package com.example.transapp.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favstations")
public class FavoriteStations {
    /** Clase utilizada para guardar favoritos del usuario */

    @PrimaryKey
    private Long id;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String hopen;
    @ColumnInfo
    private float latitude;
    @ColumnInfo
    private float longitude;
    @ColumnInfo
    private String hclose;
    @ColumnInfo
    private boolean ptoInfo;
    @ColumnInfo
    private boolean wifi;
    @ColumnInfo
    private boolean busStation;
    @ColumnInfo
    private boolean taxiStation;

    public FavoriteStations(Long id, String name, String hopen, float latitude,
                            float longitude, String hclose, boolean ptoInfo,
                            boolean wifi, boolean busStation, boolean taxiStation) {
        this.id = id;
        this.name = name;
        this.hopen = hopen;
        this.latitude = latitude;
        this.longitude = longitude;
        this.hclose = hclose;
        this.ptoInfo = ptoInfo;
        this.wifi = wifi;
        this.busStation = busStation;
        this.taxiStation = taxiStation;
    }

    public FavoriteStations(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHopen() {
        return hopen;
    }

    public void setHopen(String hopen) {
        this.hopen = hopen;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
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
