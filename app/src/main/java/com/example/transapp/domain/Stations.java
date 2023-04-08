package com.example.transapp.domain;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Stations {


    private Long id;
    private String name;
    private LocalTime hopen;
    private float latitude;
    private float longitude;
    private LocalTime hclose;
    private boolean ptoInfo;
    private boolean wifi;
    private boolean busStation;
    private boolean taxiStation;

    public Stations(){}

    public Stations(Long id, String name, LocalTime hopen, float latitude, float longitude,
                    LocalTime hclose, boolean ptoInfo, boolean wifi, boolean busStation,
                    boolean taxiStation) {
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

    public LocalTime getHopen() {
        return hopen;
    }
    public String getHoraOpenComoString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return hopen.format(formatter);
    }

    public void setHopen(LocalTime hopen) {
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

    public LocalTime getHclose() {
        return hclose;
    }
    public String getHoraCloseComoString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return hclose.format(formatter);
    }

    public void setHclose(LocalTime hclose) {
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
