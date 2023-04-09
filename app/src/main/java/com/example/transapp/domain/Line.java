package com.example.transapp.domain;

import java.util.List;

public class Line {

    private long id;
    private String codeLine;
    private String color;
    private int stopTime;
    private List<Stations> stationsList;

    public Line(long id, String codeLine, String color, int stopTime, List<Stations> stations) {
        this.id = id;
        this.codeLine = codeLine;
        this.color = color;
        this.stopTime = stopTime;
        this.stationsList = stations;
    }

    public Line(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodeLine() {
        return codeLine;
    }

    public void setCodeLine(String codeLine) {
        this.codeLine = codeLine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getStopTime() {
        return stopTime;
    }

    public void setStopTime(int stopTime) {
        this.stopTime = stopTime;
    }

    public List<Stations> getStationsList() {
        return stationsList;
    }

    public void setStationsList(List<Stations> stationsList) {
        this.stationsList = stationsList;
    }
}
