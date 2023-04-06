package com.example.transapp.domain;

import java.time.LocalTime;

public class Stations {


    private Long id;
    private String name;
    private LocalTime hopen; //TODO Revisar Json
    private float latitude;
    private float longitude;
    private LocalTime hclose; //TODO Revisar Json
    private boolean ptoInfo;
    private boolean wifi;
    private boolean busStation;
    private boolean taxiStation;

}
