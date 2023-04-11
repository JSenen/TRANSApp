package com.example.transapp.domain;

import java.time.LocalTime;

public class LineDataSingleton {

    /** Clase usada para pasar datos entre Activities*/
    private static LineDataSingleton instance = null;


    private long id;
    private String codeLine;
    private String color;
    private String firstTime;
    private String lastTime;
    private int stopTime;

    public static LineDataSingleton getInstance() {
        if (instance == null) {
            instance = new LineDataSingleton();
        }
        return instance;
    }

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

    public int getStopTime() {
        return stopTime;
    }

    public void setStopTime(int stopTime) {
        this.stopTime = stopTime;
    }
}
