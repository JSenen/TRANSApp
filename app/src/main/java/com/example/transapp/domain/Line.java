package com.example.transapp.domain;

import java.util.List;

public class Line {

    private long id;
    private String codeLine;
    private String color;
    private String firstTime;
    private String lastTime;
    private int stopTime;
    private List<Stations> stationsList;

    public Line(long id, String codeLine, String color, String firstTime, String lastTime, int stopTime, List<Stations> stations) {
        this.id = id;
        this.codeLine = codeLine;
        this.color = color;
        this.firstTime = firstTime;
        this.lastTime = lastTime;
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

    public String getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(String firstTime) {
        this.firstTime = firstTime;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

}
