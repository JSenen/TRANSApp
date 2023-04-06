package com.example.transapp.domain;

import java.time.LocalTime;

public class Lines {

    private long id;
    private String codeLine;
    private String color;
    private LocalTime firstTime; //TODO Revisar Json
    private LocalTime lastTime; //TODO Revisar Json
    private int stopTime;

    public Lines(long id, String codeLine, String color, LocalTime firstTime, LocalTime lastTime, int stopTime) {
        this.id = id;
        this.codeLine = codeLine;
        this.color = color;
        this.firstTime = firstTime;
        this.lastTime = lastTime;
        this.stopTime = stopTime;
    }
    public Lines(){}

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

    public LocalTime getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(LocalTime firstTime) {
        this.firstTime = firstTime;
    }

    public LocalTime getLastTime() {
        return lastTime;
    }

    public void setLastTime(LocalTime lastTime) {
        this.lastTime = lastTime;
    }

    public int getStopTime() {
        return stopTime;
    }

    public void setStopTime(int stopTime) {
        this.stopTime = stopTime;
    }
}
