package com.example.transapp.domain;

import java.time.LocalTime;

public class Line {

    private long id;
    private String codeLine;
    private String color;
    private int stopTime;

    public Line(long id, String codeLine, String color, int stopTime) {
        this.id = id;
        this.codeLine = codeLine;
        this.color = color;

        this.stopTime = stopTime;
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

}
