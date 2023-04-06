package com.example.transapp.domain;

import java.time.LocalTime;

public class Lines {

    private long id;
    private String codeLine;
    private String color;
    private LocalTime firstTime; //TODO Revisar Json
    private LocalTime lastTime; //TODO Revisar Json
    private int stopTime;
}
